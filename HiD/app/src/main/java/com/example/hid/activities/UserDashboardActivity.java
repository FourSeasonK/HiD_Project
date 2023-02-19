package com.example.hid.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.hid.R;
import com.example.hid.databinding.ActivityUserDashboardBinding;

public class UserDashboardActivity extends NavigationActivity {

    private static final String TAG = UserDashboardActivity.class.getSimpleName();
    ActivityUserDashboardBinding activityUserDashboardBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_user_dashboard);

        activityUserDashboardBinding = ActivityUserDashboardBinding.inflate(getLayoutInflater());
        View rootView = getLayoutInflater().inflate(R.layout.activity_user_dashboard, frameLayout);

    }
}