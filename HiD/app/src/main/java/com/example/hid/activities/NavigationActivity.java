package com.example.hid.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import android.content.ClipData;
import android.content.SharedPreferences;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.hid.R;
import com.google.android.material.navigation.NavigationView;

public class NavigationActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    protected FrameLayout frameLayout;
    private Context context;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    View logInOut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        initView();
        frameLayout = (FrameLayout) findViewById(R.id.container);
        drawer = findViewById(R.id.drawer_layout);
        logInOut = findViewById(R.id.nav_login);
        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);

    }

    private void initView(){

        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open_nav, R.string.close_nav);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

//        SharedPreferences sh = getSharedPreferences("LogInSuccess", 0);
//        boolean logIn = sh.getBoolean("LOGINVALUE", false);
//        Log.d("LogInBoolValue", String.valueOf(logIn));

        menuItem.setChecked(true);

//        if (logIn == true) {
//            logInOut.setBackgroundResource(R.drawable.nav_logout);

            switch (menuItem.getItemId()) {
                case R.id.nav_home:
                    Intent switchToHomeIntent = new Intent(this, HomeActivity.class);
                    startActivity(switchToHomeIntent);
                    break;

                case R.id.nav_boxBreath:
                    Intent switchToBoxBIntent = new Intent(this, BoxBreathingActivity.class);
                    startActivity(switchToBoxBIntent);
                    break;

                case R.id.nav_donotB:
                    Intent switchToDonotBIntent = new Intent(this, DoNotBlameActivity.class);
                    startActivity(switchToDonotBIntent);
                    break;

                case R.id.nav_createD:
                    Intent switchToCreateDIntent = new Intent(this, CreateMyDActivity.class);
                    startActivity(switchToCreateDIntent);
                    break;

                case R.id.nav_DForum:
                    Intent switchToDForumIntent = new Intent(this, ShareForumActivity.class);
                    startActivity(switchToDForumIntent);
                    break;

                case R.id.nav_contactP:
                    Intent switchToContactPIntent = new Intent(this, ContactPeopleActivity.class);
                    startActivity(switchToContactPIntent);
                    break;

                case R.id.nav_userDashboaed:
                    Intent switchToUserDashBIntent = new Intent(this, UserDashboardActivity.class);
                    startActivity(switchToUserDashBIntent);
                    break;

                case R.id.nav_login:
                    Intent switchToLogInIntent = new Intent(this, LogInOutActivity.class);
                    startActivity(switchToLogInIntent);
                    break;

                case R.id.nav_userInfo:
                    Intent switchToUserInfoIntent = new Intent(this, UserDashboardActivity.class);
                    startActivity(switchToUserInfoIntent);

                case R.id.nav_password:
                    Intent switchToPasswordInIntent = new Intent(this, ResetPasswordActivity.class);
                    startActivity(switchToPasswordInIntent);
                    break;
            }
//        } else {
//            switch (menuItem.getItemId()) {
//                case R.id.nav_home:
//                    Intent switchToHomeIntent = new Intent(this, HomeActivity.class);
//                    startActivity(switchToHomeIntent);
//                    break;
//
//                case R.id.nav_boxBreath:
//                    Intent switchToBoxBIntent = new Intent(this, BoxBreathingActivity.class);
//                    startActivity(switchToBoxBIntent);
//                    break;
//
//                case R.id.nav_donotB:
//                    Intent switchToDonotBIntent = new Intent(this, DoNotBlameActivity.class);
//                    startActivity(switchToDonotBIntent);
//                    break;
//
//                case R.id.nav_createD:
//                    Intent switchToCreateDIntent = new Intent(this, CreateMyDActivity.class);
//                    startActivity(switchToCreateDIntent);
//                    break;
//
//                case R.id.nav_DForum:
//                    Intent switchToDForumIntent = new Intent(this, ShareForumActivity.class);
//                    startActivity(switchToDForumIntent);
//                    break;
//
//                case R.id.nav_contactP:
//                    Intent switchToContactPIntent = new Intent(this, ContactPeopleActivity.class);
//                    startActivity(switchToContactPIntent);
//                    break;
//
//                case R.id.nav_userDashboaed:
//                    Intent switchToUserDashBIntent = new Intent(this, UserDashboardActivity.class);
//                    startActivity(switchToUserDashBIntent);
//                    break;
//
//                case R.id.nav_login:
//                    Intent switchToLogInIntent = new Intent(this, LogInOutActivity.class);
//                    startActivity(switchToLogInIntent);
//                    break;
//
//            }
//        }

            return true;
        }


    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }
}