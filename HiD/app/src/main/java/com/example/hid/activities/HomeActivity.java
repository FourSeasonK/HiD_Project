package com.example.hid.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.hid.R;

public class HomeActivity extends AppCompatActivity {

    ImageView imgbtnBoxBreathing;
    ImageView imgbtnDonotBlame;
    ImageView imgbtnCreateMyD;
    ImageView imgbtnContactPeople;
    ImageView imgbtnShareForum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        imgbtnBoxBreathing = findViewById(R.id.imgbtnBoxBreath);
        imgbtnDonotBlame = findViewById(R.id.imgbtnDonotBlame);
        imgbtnCreateMyD = findViewById(R.id.imgbtnCreadD);
        imgbtnContactPeople = findViewById(R.id.imgbtnContactP);
        imgbtnShareForum = findViewById(R.id.imgbtnShareD);

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