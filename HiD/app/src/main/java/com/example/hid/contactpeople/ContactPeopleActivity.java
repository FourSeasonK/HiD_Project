package com.example.hid.contactpeople;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.hid.R;
import com.example.hid.activities.NavigationActivity;
import com.example.hid.databinding.ActivityContactPeopleBinding;

public class ContactPeopleActivity extends NavigationActivity {

    private static final String TAG = ContactPeopleActivity.class.getSimpleName();
    ActivityContactPeopleBinding activityContactPeopleBinding;

    private Button btnCPText, btnCPCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_contact_people);

        activityContactPeopleBinding = ActivityContactPeopleBinding.inflate(getLayoutInflater());
        View rootView = getLayoutInflater().inflate(R.layout.activity_contact_people, frameLayout);

        btnCPText = rootView.findViewById(R.id.btnCPText);
        btnCPCall = rootView.findViewById(R.id.btnCPCall);

        btnCPText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = getPackageManager().getLaunchIntentForPackage("com.android.messaging");
                startActivity(intent);

            }
        });

        btnCPCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                startActivity(intent);
            }
        });

    }
}