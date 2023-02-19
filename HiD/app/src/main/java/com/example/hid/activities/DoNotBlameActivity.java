package com.example.hid.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.hid.R;
import com.example.hid.databinding.ActivityDoNotBlameBinding;

public class DoNotBlameActivity extends NavigationActivity  {

    private static final String TAG = DoNotBlameActivity.class.getSimpleName();
    ActivityDoNotBlameBinding activityDoNotBlameBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_do_not_blame);

        activityDoNotBlameBinding = ActivityDoNotBlameBinding.inflate(getLayoutInflater());
        View rootView = getLayoutInflater().inflate(R.layout.activity_do_not_blame, frameLayout);

    }
}