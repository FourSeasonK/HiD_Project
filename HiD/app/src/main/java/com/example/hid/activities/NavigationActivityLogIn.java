package com.example.hid.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.hid.R;
import com.example.hid.boxbreath.BoxBreathingActivityLogIn;
import com.example.hid.contactpeople.ContactPeopleActivityLogIn;
import com.example.hid.created.CreateMyDActivityLogIn;
import com.example.hid.dforum.MyDiaryActivityLogIn;
import com.example.hid.lovemyself.DoNotBlameActivityLogIn;
import com.example.hid.userdashboard.UserDashboardActivityLogIn;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class NavigationActivityLogIn extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = NavigationActivityLogIn.class.getSimpleName();

    protected FrameLayout frameLayout;
    private Context context;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    View logInOut;
    TextView userEmail;
//    ArrayList<String> userInfo;

    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;
    boolean checkUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

            auth = FirebaseAuth.getInstance();
            initView();
//            loginView();


        frameLayout = (FrameLayout) findViewById(R.id.container);
//        drawer = findViewById(R.id.drawer_layout);
//        NavigationView navigationView = findViewById(R.id.nav_view);
////
//        navigationView.setNavigationItemSelectedListener(this);

    }

    private void initView(){

        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        //Change the menu list
        navigationView.getMenu().clear();
        navigationView.inflateMenu(R.menu.nav_menulogin);

        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open_nav, R.string.close_nav);
        drawer.addDrawerListener(toggle);

        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

    }

//    private void loginView(){
//        toolbar = findViewById(R.id.toolbarlogin);
//        drawer = findViewById(R.id.drawer_layout_login);
//        NavigationView navigationView = findViewById(R.id.nav_viewlogin);
//
//        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open_nav, R.string.close_nav);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();
//        navigationView.setNavigationItemSelectedListener(this);
//    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        menuItem.setChecked(true);

            switch (menuItem.getItemId()) {
                case R.id.nav_home:
                    Intent switchToHomeIntent = new Intent(this, HomeActivityLogInD.class);
//                    Intent switchToHomeIntent = new Intent(this, HomeActivityLogInD.class);
                    startActivity(switchToHomeIntent);
                    break;

                case R.id.nav_boxBreath:
                    Intent switchToBoxBIntent = new Intent(this, BoxBreathingActivityLogIn.class);
                    startActivity(switchToBoxBIntent);
                    break;

                case R.id.nav_donotB:
                    Intent switchToDonotBIntent = new Intent(this, DoNotBlameActivityLogIn.class);
                    startActivity(switchToDonotBIntent);
                    break;

                case R.id.nav_createD:
                    Intent switchToCreateDIntent = new Intent(this, CreateMyDActivityLogIn.class);
                    startActivity(switchToCreateDIntent);
                    break;

//                case R.id.nav_DForum:
//                    Intent switchToDForumIntent = new Intent(this, ShareForumActivity.class);
//                    startActivity(switchToDForumIntent);
//                    break;

                case R.id.nav_diary:
                    Intent switchToDiary = new Intent(this, MyDiaryActivityLogIn.class);
                    startActivity( switchToDiary);
                    break;

                case R.id.nav_contactP:
                    Intent switchToContactPIntent = new Intent(this, ContactPeopleActivityLogIn.class);
                    startActivity(switchToContactPIntent);
                    break;

                case R.id.nav_needHelp:

                    Intent switchToNeedHelp = new Intent(this, NeedHelpActivityLogin.class);
                    startActivity(switchToNeedHelp);
                    break;

                case R.id.nav_userDashboaed:
                    Intent switchToUserDashBIntent = new Intent(this, UserDashboardActivityLogIn.class);
                    startActivity(switchToUserDashBIntent);
                    break;

                case R.id.nav_userInfo:
                    Intent switchToUserInfoIntent = new Intent(this, UpdateUserInfoActivityLogIn.class);
                    startActivity(switchToUserInfoIntent);
                    break;

//                case R.id.nav_password:
//                    Intent switchToPasswordInIntent = new Intent(this, ResetPasswordActivityLogIn.class);
//                    startActivity(switchToPasswordInIntent);
//                    break;

                case R.id.nav_logout:
                    auth.signOut();
                    Intent switchToLogOutIntent = new Intent(this, LogInOutActivity.class);
                    startActivity(switchToLogOutIntent);
                    finish();
                    Toast.makeText(NavigationActivityLogIn.this, "Logout Successful", Toast.LENGTH_SHORT).show();
                    break;
            }

            return true;
        }


    public void changeUserName(){

        ArrayList<String> userInfo = new ArrayList<>();
        checkCurrentUser(userInfo);

        final TextView userEmail = findViewById(R.id.txtUserName);

        userEmail.setText(userInfo.get(0));

        Log.d(this.getClass().getSimpleName(), userEmail.getText().toString());
        Toast.makeText(this, "Welcome! " + userEmail.getText().toString(),
                Toast.LENGTH_LONG).show();
    }

    public void checkCurrentUser(ArrayList<String> userInfo) {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        userInfo = new ArrayList<>();

        if (user != null) {
            // User is signed in
            String email = user.getEmail();
            String uid = user.getUid();
            //String name = user.getDisplayName();
            //emailAdd = email;
            userInfo.add(email);
            userInfo.add(uid);
            checkUser = true;

        } else {
            checkUser = false;
        }
    }
}