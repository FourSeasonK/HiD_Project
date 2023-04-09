package com.example.hid.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hid.R;
import com.example.hid.dialog.ResetpasswordDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogInOutActivity extends AppCompatActivity implements ResetpasswordDialog.ResetPasswordDialogListener {

    private static final String TAG = LogInOutActivity.class.getSimpleName();
//    ActivityLogInOutBinding activityLogInOutBinding;

    String EAMILTAG = "EMAIL_AUTH";

    Button btnStartNow, btnLogIn;
    TextInputLayout loginInputEmail, loginInputPassword;
    EditText email, password;
    TextView txtCreatNewAccount, txtForgotPassword;
    String strEmail, strPassword;
    ProgressBar progressBar;

    private FirebaseAuth mAuth;
    private boolean logIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_out);

//        activityLogInOutBinding = ActivityLogInOutBinding.inflate(getLayoutInflater());
//        View rootView = getLayoutInflater().inflate(R.layout.activity_log_in_out, frameLayout);

        mAuth = FirebaseAuth.getInstance();

        btnStartNow = findViewById(R.id.btnStartNow);
        loginInputEmail = findViewById(R.id.LogIn_input_layout_email);
        email = findViewById(R.id.edittxtLogInEmail);
        loginInputPassword = findViewById(R.id.LogIn_input_layout_password);
        password = findViewById(R.id.edittxtLogInPassword);
        txtCreatNewAccount = findViewById(R.id.txtCreateNewAccount);
        btnLogIn = findViewById(R.id.btnLogIn);
        txtForgotPassword = findViewById(R.id.txtLogInForgotpPassword);
        progressBar = findViewById(R.id.progressBarLogIn);

        strEmail = email.getText().toString().trim();
        strPassword = password.getText().toString().trim();

        btnStartNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogInOutActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLogIn();
            }
        });

        txtCreatNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogInOutActivity.this, CreateAccountActivity.class);
                startActivity(intent);
            }
        });

        txtForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
//                Intent intent = new Intent(LogInOutActivity.this, ResetPasswordActivityLogIn.class);
//                startActivity(intent);
            }
        });
    }

    public void openDialog(){
        ResetpasswordDialog resetpasswordDialog = new ResetpasswordDialog();
        resetpasswordDialog.show(getSupportFragmentManager(), "ResetPassword Dialog");
    }


    public void onLogIn(){

        String loginEmail = email.getText().toString().trim();
        String loginPassword = password.getText().toString().trim();

        if(!checkEmail()){
            return;
        }
        if(!checkPassword()){
            return;
        }

        loginInputEmail.setErrorEnabled(false);
        loginInputPassword.setErrorEnabled(false);

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(loginEmail, loginPassword).addOnCompleteListener(LogInOutActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d(TAG, "signInWithEmail:onComplete: " + task.isSuccessful());

                if(!task.isSuccessful()){
                    Log.d(TAG, "Authentication failed. " + task.getException());
                    Toast.makeText(getApplicationContext(), "LogIn failed. Check your Information", Toast.LENGTH_SHORT).show();
                    logIn = false;

                } else {
                    Toast.makeText(getApplicationContext(), "LogIn Successfully", Toast.LENGTH_SHORT).show();
                    logIn = true;

                    SharedPreferences sharedPreferences = getSharedPreferences("LogInSuccess",MODE_PRIVATE);
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.putBoolean("LOGINVALUE", logIn);
                    edit.putString("LOGINEMAIL", strPassword);
                    edit.commit();

//                    Intent intent = new Intent(LogInOutActivity.this, HomeActivityLogIn.class);
                    Intent intent = new Intent(LogInOutActivity.this, HomeActivityLogInD.class);
//                    Intent intent = new Intent(LogInOutActivity.this, HomeActivity.class);
//                    intent.putExtra("LOGINVALUE", logIn);
//                    intent.putExtra("LOGINEMAIL", strPassword);
                    startActivity(intent);

                    finish();
                }
            }
        });

    }

    private boolean checkEmail(){
        String emailValue = email.getText().toString().trim();
        Log.d("email value: ", emailValue);
        strEmail = emailValue;

        if(emailValue.isEmpty() || !isEmailValid(emailValue)){
            loginInputEmail.setErrorEnabled(true);
            loginInputEmail.setError(getString(R.string.err_msg_email));
            email.setError(getString(R.string.err_msg_required));
            requestFocus(email);
            return false;
        }
        loginInputEmail.setErrorEnabled(false);
        return true;
    }

    private boolean checkPassword(){
        String passwordValue = password.getText().toString().trim();
        Log.d("password value: ", passwordValue);
        strPassword = passwordValue;

        if (passwordValue.isEmpty() || !isPasswordValid(passwordValue)) {

            loginInputPassword.setError(getString(R.string.err_msg_password));
            password.setError(getString(R.string.err_msg_required));
            requestFocus(password);
            return false;
        }
        loginInputPassword.setErrorEnabled(false);
        return true;
    }

    private static boolean isEmailValid(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private static boolean isPasswordValid(String password) {
        return (password.length() >= 6);
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void applyTexts(String email) {

        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(LogInOutActivity.this, "Email sent", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}