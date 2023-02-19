package com.example.hid.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.hid.R;
import com.example.hid.databinding.ActivityCreateAccountBinding;

public class CreateAccountActivity extends NavigationActivity {

    private static final String TAG = CreateAccountActivity.class.getSimpleName();
    ActivityCreateAccountBinding activityCreateAccountBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_create_account);

        activityCreateAccountBinding = ActivityCreateAccountBinding.inflate(getLayoutInflater());
        View rootView = getLayoutInflater().inflate(R.layout.activity_create_account, frameLayout);

    }
}