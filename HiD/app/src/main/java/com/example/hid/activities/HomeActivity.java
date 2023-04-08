package com.example.hid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.hid.R;
import com.example.hid.boxbreath.BoxBreathingActivity;
import com.example.hid.contactpeople.ContactPeopleActivity;
import com.example.hid.created.CreateMyDActivity;
import com.example.hid.databinding.ActivityHomeBinding;
import com.example.hid.dforum.ShareForumActivity;
import com.example.hid.lovemyself.DoNotBlameActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends NavigationActivity {

    private static final String TAG = HomeActivity.class.getSimpleName();
    ActivityHomeBinding activityHomeBinding;

    ImageView imgbtnBoxBreathing;
    ImageView imgbtnDonotBlame;
    ImageView imgbtnCreateMyD;
    ImageView imgbtnContactPeople;
    ImageView imgbtnNeedHelp;
    NavigationView navigationView;

    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;
    boolean checkUser;
    String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home);

        checkUser();
        //navigationView = findViewById(R.id.nav_view);

//        if(checkUser == true){
//
//            navigationView.getMenu().clear();
//            navigationView = findViewById(R.id.nav_viewlogin);
//            navigationView.inflateMenu(R.menu.nav_menulogin);
//            TextView nav_userName = navigationView.findViewById(R.id.txtUserName);
//            nav_userName.setText(userEmail);
//
//        } else {
//            navigationView = findViewById(R.id.nav_view);
//            navigationView.getMenu().clear();
//            navigationView.inflateMenu(R.menu.nav_menu);
//        }


        activityHomeBinding = ActivityHomeBinding.inflate(getLayoutInflater());

        View rootView = getLayoutInflater().inflate(R.layout.activity_home, frameLayout);

        imgbtnBoxBreathing = rootView.findViewById(R.id.imgbtnBoxBreath);
        imgbtnDonotBlame = rootView.findViewById(R.id.imgbtnDonotBlame);
        imgbtnCreateMyD = rootView.findViewById(R.id.imgbtnCreadD);
        imgbtnContactPeople = rootView.findViewById(R.id.imgbtnContactP);
        imgbtnNeedHelp = rootView.findViewById(R.id.imgbtnNeedHelp);

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

        imgbtnNeedHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, NeedHelpActivity.class);
                startActivity(intent);
            }
        });
    }

    public void checkUser(){
        auth = FirebaseAuth.getInstance();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    checkUser = true;
                    String userEmail = user.getEmail();
//                    Log.d(TAG, userEmail + "");
                    Toast.makeText(HomeActivity.this, userEmail, Toast.LENGTH_SHORT).show();
                } else {
                    checkUser = false;
                }
            }
        };
    }
}