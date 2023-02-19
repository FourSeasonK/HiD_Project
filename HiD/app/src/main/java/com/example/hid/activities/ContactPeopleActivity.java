package com.example.hid.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.hid.R;
import com.example.hid.databinding.ActivityContactPeopleBinding;

public class ContactPeopleActivity extends NavigationActivity {

    private static final String TAG = ContactPeopleActivity.class.getSimpleName();
    ActivityContactPeopleBinding activityContactPeopleBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_contact_people);

        activityContactPeopleBinding = ActivityContactPeopleBinding.inflate(getLayoutInflater());
        View rootView = getLayoutInflater().inflate(R.layout.activity_contact_people, frameLayout);
    }
}