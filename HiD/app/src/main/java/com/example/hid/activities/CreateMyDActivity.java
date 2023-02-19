package com.example.hid.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.hid.R;
import com.example.hid.databinding.ActivityCreateMyDactivityBinding;

public class CreateMyDActivity extends NavigationActivity {

    private static final String TAG = CreateMyDActivity.class.getSimpleName();
    ActivityCreateMyDactivityBinding activityCreateMyDactivityBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_create_my_dactivity);

        activityCreateMyDactivityBinding = ActivityCreateMyDactivityBinding.inflate(getLayoutInflater());
        View rootView = getLayoutInflater().inflate(R.layout.activity_create_my_dactivity, frameLayout);
    }
}