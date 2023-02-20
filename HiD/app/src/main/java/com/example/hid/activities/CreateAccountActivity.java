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
import com.example.hid.databinding.ActivityCreateAccountBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CreateAccountActivity extends NavigationActivity {

    private static final String TAG = CreateAccountActivity.class.getSimpleName();
    ActivityCreateAccountBinding activityCreateAccountBinding;

    private TextInputLayout CA_input_layout_email, CA_input_layout_password, CA_input_layout_confirmpassword;
    private EditText firstName, lastName, email, password, confirmPassword;
    private ProgressBar progressBar;
    private Button btnSave;
    private TextView txtMoveToLogin;
    String strFirstName, strLastName, strEmail, strPassword;
    private boolean logIn = false;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_create_account);

        activityCreateAccountBinding = ActivityCreateAccountBinding.inflate(getLayoutInflater());
        View rootView = getLayoutInflater().inflate(R.layout.activity_create_account, frameLayout);

        mAuth = FirebaseAuth.getInstance();

        firstName = rootView.findViewById(R.id.edittxtCAFirstName);
        lastName = rootView.findViewById(R.id.edittxtCALastName);
        CA_input_layout_email = rootView.findViewById(R.id.CA_input_layout_email);
        email = rootView.findViewById(R.id.edittxtCAEmail);
        CA_input_layout_password = rootView.findViewById(R.id.CA_input_layout_password);
        password = rootView.findViewById(R.id.edittxtCAPassword);
//        CA_input_layout_confirmpassword = rootView.findViewById(R.id.CAConfirm_input_layout_password);
//        confirmPassword = rootView.findViewById(R.id.edittxtCAConPassword);
        btnSave = rootView.findViewById(R.id.btnCASave);
        txtMoveToLogin = rootView.findViewById(R.id.txtMoveToLogIn);
        progressBar = rootView.findViewById(R.id.progressBar);

        strFirstName = firstName.getText().toString().trim();
        strLastName = lastName.getText().toString().trim();
//        strEmail = email.getText().toString().trim();
//        strPassword = password.getText().toString().trim();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCreateAccount();
            }
        });

        txtMoveToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateAccountActivity.this, LogInOutActivity.class);
                startActivity(intent);
            }
        });

    }

    public void onCreateAccount(){
//        String strEmail = email.getText().toString().trim();
//        String strPassword = password.getText().toString().trim();
//        String strConfirmPassword = confirmPassword.getText().toString().trim();

        if(!checkEmail()){
            return;
        }
        if(!checkPassword()){
            return;
        }

        CA_input_layout_email.setErrorEnabled(false);
        CA_input_layout_password.setErrorEnabled(false);
        //CA_input_layout_confirmpassword.setErrorEnabled(false);

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(strEmail, strPassword).addOnCompleteListener(CreateAccountActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d(TAG, "createUserWithEmail:onComplete: " + task.isSuccessful());

                if(!task.isSuccessful()){
                    Log.d(TAG, "Authentication failed. " + task.getException());
                    logIn = false;

                } else {
                    Toast.makeText(getApplicationContext(), "Create Account Successfully", Toast.LENGTH_SHORT).show();
                    logIn = true;

                    SharedPreferences sharedPreferences = getSharedPreferences("LogInSuccess",MODE_PRIVATE);
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.putBoolean("LOGINVALUE", logIn);
                    edit.putString("FIRSTNAME", strFirstName);
                    edit.putString("LASTNAME", strLastName);
                    edit.putString("LASTNAME", strLastName);
                    edit.commit();

                    Intent intent = new Intent(CreateAccountActivity.this, HomeActivity.class);
//                    intent.putExtra("LOGINVALUE", logIn);
//                    intent.putExtra("FIRSTNAME", strFirstName);
//                    intent.putExtra("LASTNAME", strLastName);
//                    intent.putExtra("LASTNAME", strLastName);
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
            CA_input_layout_email.setErrorEnabled(true);
            CA_input_layout_email.setError(getString(R.string.err_msg_email));
            email.setError(getString(R.string.err_msg_required));
            requestFocus(email);
            return false;
        }
        CA_input_layout_email.setErrorEnabled(false);
        return true;
    }

    private boolean checkPassword(){
        String passwordValue = password.getText().toString().trim();
        Log.d("password value: ", passwordValue);
        strPassword = passwordValue;

        if (passwordValue.isEmpty() || !isPasswordValid(passwordValue)) {

            CA_input_layout_password.setError(getString(R.string.err_msg_password));
            password.setError(getString(R.string.err_msg_required));
            requestFocus(password);
            return false;
        }
        CA_input_layout_password.setErrorEnabled(false);
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
}