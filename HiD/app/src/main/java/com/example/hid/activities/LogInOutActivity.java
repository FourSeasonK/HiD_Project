package com.example.hid.activities;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hid.databinding.ActivityLogInOutBinding;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hid.R;

public class LogInOutActivity extends NavigationActivity {

    private static final String TAG = LogInOutActivity.class.getSimpleName();
    ActivityLogInOutBinding activityLogInOutBinding;


    Button btnStartNow;
    TextView txtCreatNewAccount;
    TextView txtForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_log_in_out);

        activityLogInOutBinding = ActivityLogInOutBinding.inflate(getLayoutInflater());
        View rooView = getLayoutInflater().inflate(R.layout.activity_log_in_out, frameLayout);

        btnStartNow = rooView.findViewById(R.id.btnStartNow);
        txtCreatNewAccount = rooView.findViewById(R.id.txtCreateNewAccount);
        txtForgotPassword = rooView.findViewById(R.id.txtLogInForgotpPassword);

        btnStartNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogInOutActivity.this, HomeActivity.class);
                startActivity(intent);
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
                Intent intent = new Intent(LogInOutActivity.this, ResetPasswordActivity.class);
                startActivity(intent);
            }
        });
    }
}