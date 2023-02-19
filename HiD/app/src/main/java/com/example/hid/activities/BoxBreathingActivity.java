package com.example.hid.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.hid.R;
import com.example.hid.databinding.ActivityBoxBreathingBinding;

public class BoxBreathingActivity extends NavigationActivity {

    private static final String TAG = BoxBreathingActivity.class.getSimpleName();
    ActivityBoxBreathingBinding activityBoxBreathingBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_box_breathing);

        activityBoxBreathingBinding = ActivityBoxBreathingBinding.inflate(getLayoutInflater());
        View rootView = getLayoutInflater().inflate(R.layout.activity_box_breathing, frameLayout);

    }
}