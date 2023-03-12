package com.example.hid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.hid.R;
import com.example.hid.boxbreath.BoxBreathingActivity;
import com.example.hid.contactpeople.ContactPeopleActivity;
import com.example.hid.created.CreateMyDActivity;
import com.example.hid.databinding.ActivityHomeBinding;
import com.example.hid.dforum.ShareForumActivity;
import com.example.hid.lovemyself.DoNotBlameActivity;

public class HomeActivity extends NavigationActivity {

    private static final String TAG = HomeActivity.class.getSimpleName();
    ActivityHomeBinding activityHomeBinding;

    ImageView imgbtnBoxBreathing;
    ImageView imgbtnDonotBlame;
    ImageView imgbtnCreateMyD;
    ImageView imgbtnContactPeople;
    ImageView imgbtnShareForum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home);

        activityHomeBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        View rootView = getLayoutInflater().inflate(R.layout.activity_home, frameLayout);

        imgbtnBoxBreathing = rootView.findViewById(R.id.imgbtnBoxBreath);
        imgbtnDonotBlame = rootView.findViewById(R.id.imgbtnDonotBlame);
        imgbtnCreateMyD = rootView.findViewById(R.id.imgbtnCreadD);
        imgbtnContactPeople = rootView.findViewById(R.id.imgbtnContactP);
        imgbtnShareForum = rootView.findViewById(R.id.imgbtnShareD);

        imgbtnBoxBreathing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, BoxBreathingActivity.class);
                startActivity(intent);
            }
        });

        imgbtnDonotBlame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, DoNotBlameActivity.class);
                startActivity(intent);
            }
        });

        imgbtnCreateMyD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, CreateMyDActivity.class);
                startActivity(intent);
            }
        });

        imgbtnContactPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ContactPeopleActivity.class);
                startActivity(intent);
            }
        });

        imgbtnShareForum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ShareForumActivity.class);
                startActivity(intent);
            }
        });
    }
}