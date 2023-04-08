package com.example.hid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.hid.R;
import com.example.hid.boxbreath.BoxBreathingActivity;
import com.example.hid.boxbreath.BoxBreathingActivityLogIn;
import com.example.hid.contactpeople.ContactPeopleActivityLogIn;
import com.example.hid.created.CreateMyDActivity;
import com.example.hid.created.CreateMyDActivityLogIn;
import com.example.hid.dao.HiDUserInformationDAO;
import com.example.hid.databinding.ActivityHomeloginBinding;
import com.example.hid.dforum.MyDiaryActivityLogIn;
import com.example.hid.lovemyself.DoNotBlameActivityLogIn;
import com.example.hid.model.HiDUserInformation;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeActivityLogInD extends NavigationActivityLogIn {

    private static final String TAG = HomeActivityLogInD.class.getSimpleName();
//    ActivityHomeBinding activityHomeBinding;
    ActivityHomeloginBinding activityHomeloginBinding;

    ImageView imgbtnBoxBreathing, imgbtnDonotBlame, imgbtnCreateMyD, imgbtnContactPeople, imgbtnNeedHelp, imgbtnDiary;
    NavigationView navigationView;

    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;
    boolean checkUser;

    HiDUserInformationDAO dao;
    String userEmail;
    ArrayList<HiDUserInformation> userInforList = new ArrayList<>();
    String key1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_homelogin);

        //navigationView = findViewById(R.id.nav_view);

        //dao = new HiDUserInformationDAO();
        checkUser();
        //loadData();

//        if(checkUser == true){
//
//            TextView nav_userName = navigationView.findViewById(R.id.txtUserName);
//            nav_userName.setText(userEmail);
//
//        }


        activityHomeloginBinding = ActivityHomeloginBinding.inflate(getLayoutInflater());
        View rootView = getLayoutInflater().inflate(R.layout.activity_homelogind, frameLayout);

        imgbtnBoxBreathing = rootView.findViewById(R.id.imgbtnBoxBreath);
        imgbtnDonotBlame = rootView.findViewById(R.id.imgbtnDonotBlame);
        imgbtnCreateMyD = rootView.findViewById(R.id.imgbtnCreadD);
        imgbtnContactPeople = rootView.findViewById(R.id.imgbtnContactP);
        imgbtnNeedHelp = rootView.findViewById(R.id.imgbtnNeedHelp);
        imgbtnDiary = rootView.findViewById(R.id.imgbtnDiary);


        imgbtnBoxBreathing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivityLogInD.this, BoxBreathingActivityLogIn.class);
                startActivity(intent);
            }
        });

        imgbtnDonotBlame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivityLogInD.this, DoNotBlameActivityLogIn.class);
                startActivity(intent);
            }
        });

        imgbtnCreateMyD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivityLogInD.this, CreateMyDActivityLogIn.class);
                startActivity(intent);
            }
        });

        imgbtnDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(HomeActivityLogInD.this, MyDiaryActivityLogIn.class);
                startActivity(intent);
            }
        });

        imgbtnContactPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivityLogInD.this, ContactPeopleActivityLogIn.class);
                startActivity(intent);
            }
        });

        imgbtnNeedHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivityLogInD.this, NeedHelpActivityLogin.class);
                startActivity(intent);
            }
        });
    }

    public void checkUser(){

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            userEmail = user.getEmail();
            Log.d(TAG, userEmail + "");
            Toast.makeText(HomeActivityLogInD.this, "Welcome, " + userEmail, Toast.LENGTH_SHORT).show();
        }
    }

    private void loadData() {
        dao.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot data : snapshot.getChildren()){

                    key1 = data.getKey();
                    Log.d(TAG, "key1: " + key1);

                    Log.d(TAG,"Load data");
                    HiDUserInformation hiDUserInformation = data.getValue(HiDUserInformation.class);
                    userInforList.add(hiDUserInformation);
                    //Log.d(TAG, "userInforList: " + userInforList.get(0).getUserEmail());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "Failed to load Notification List");
            }
        });
    }

}