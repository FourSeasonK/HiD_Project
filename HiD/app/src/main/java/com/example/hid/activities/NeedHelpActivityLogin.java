package com.example.hid.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.hid.R;
import com.example.hid.databinding.ActivityNeedHelpBinding;

public class NeedHelpActivityLogin extends NavigationActivityLogIn {

    private static final String TAG = NeedHelpActivityLogin.class.getSimpleName();
    ActivityNeedHelpBinding activityNeedHelpBinding;

    Button btnGovernmentWeb, btnProvincialWeb, btnCrisisCenterWeb, btnPsychologyWeb, btnCPAWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_need_help);

        activityNeedHelpBinding = ActivityNeedHelpBinding.inflate(getLayoutInflater());
        View rootView = getLayoutInflater().inflate(R.layout.activity_need_help, frameLayout);

        btnGovernmentWeb = rootView.findViewById(R.id.btnGovernmentWebsite);
        btnProvincialWeb = rootView.findViewById(R.id.btnProvincialWebsite);
        btnCrisisCenterWeb = rootView.findViewById(R.id.btnCrisisCenterWebsite);
        btnPsychologyWeb = rootView.findViewById(R.id.btnPsychologyWebsite);
        btnCPAWeb = rootView.findViewById(R.id.btnCPAWebsite);

        btnGovernmentWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://www.canada.ca/en/public-health/services/mental-health-services/mental-health-get-help.html"));
                startActivity(i);

            }
        });

        btnProvincialWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://ontario.cmha.ca/provincial-mental-health-supports/"));
                startActivity(i);
            }
        });

        btnCrisisCenterWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://crisiscentre.bc.ca/get-help/"));
                startActivity(i);
            }
        });

        btnPsychologyWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://www.psychologytoday.com/ca/therapists?tr=Hdr_Brand"));
                startActivity(i);
            }
        });

        btnCPAWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://cpa.ca/public/whatisapsychologist/PTassociations"));
                startActivity(i);
            }
        });
    }
}