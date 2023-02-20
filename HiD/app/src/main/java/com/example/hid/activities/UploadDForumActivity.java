package com.example.hid.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.hid.R;
import com.example.hid.databinding.ActivityUpdateDforumBinding;

public class UploadDForumActivity extends NavigationActivity {

    private static final String TAG = UploadDForumActivity.class.getSimpleName();
    ActivityUpdateDforumBinding activityUpdateDforumBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_update_dforum);

        activityUpdateDforumBinding = ActivityUpdateDforumBinding.inflate(getLayoutInflater());
        View rootView = getLayoutInflater().inflate(R.layout.activity_update_dforum, frameLayout);
    }
}