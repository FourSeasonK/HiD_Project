package com.example.hid.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.hid.R;
import com.example.hid.databinding.ActivityResetPasswordBinding;

public class ResetPasswordActivity extends NavigationActivity {

    private static final String TAG = ResetPasswordActivity.class.getSimpleName();
    ActivityResetPasswordBinding activityResetPasswordBinding;

    TextView txtGoBackToLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_reset_password);

        activityResetPasswordBinding = ActivityResetPasswordBinding.inflate(getLayoutInflater());
        View rootView = getLayoutInflater().inflate(R.layout.activity_reset_password, frameLayout);

        txtGoBackToLogIn = rootView.findViewById(R.id.txtRPGoBackLogIn);

        txtGoBackToLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ResetPasswordActivity.this, LogInOutActivity.class);
                startActivity(intent);
            }
        });
    }
}