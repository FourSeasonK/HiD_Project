package com.example.hid.contactpeople;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hid.R;
import com.example.hid.activities.NavigationActivity;
import com.example.hid.activities.NavigationActivityLogIn;
import com.example.hid.boxbreath.BoxBreathingActivityLogIn;
import com.example.hid.contactpeople.adapter.BringPhoneBookAdapter;
import com.example.hid.dao.HiDUserInformationDAO;
import com.example.hid.databinding.ActivityContactPeopleBinding;
import com.example.hid.model.BoxBreathing;
import com.example.hid.model.ContactPeople;
import com.example.hid.model.HiDUserInformation;
import com.example.hid.model.UserPhoneBook;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ContactPeopleActivityLogIn extends NavigationActivityLogIn implements BringPhoneBookAdapter.ListItemListener {

    private static final String TAG = ContactPeopleActivityLogIn.class.getSimpleName();
    ActivityContactPeopleBinding activityContactPeopleBinding;

    private Button btnCPText, btnCPCall, btnUploadPhonebook;
    ArrayList<UserPhoneBook> contactInfoList = new ArrayList<>();

    HiDUserInformationDAO dao;
    String userEmail;
    ArrayList<HiDUserInformation> userInforList = new ArrayList<>();
    String key1 , clickMethod, userNamestr, userPhoneNumberstr;
    boolean checkUser = false;
    List<ContactPeople> contactPeopleList = new ArrayList<>();

    Date today;
    SimpleDateFormat dateFormat;
    String todayDate;

    RecyclerView recyclerView;
    BringPhoneBookAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_contact_people);

        activityContactPeopleBinding = ActivityContactPeopleBinding.inflate(getLayoutInflater());
        View rootView = getLayoutInflater().inflate(R.layout.activity_contact_people, frameLayout);

        dao = new HiDUserInformationDAO();
        checkUser();
        loadData();

        bringContactInformation();

        recyclerView = rootView.findViewById(R.id.recycler_phoneBook);
        btnCPText = rootView.findViewById(R.id.btnCPText);
        btnCPCall = rootView.findViewById(R.id.btnCPCall);
        btnUploadPhonebook = rootView.findViewById(R.id.btnUploadPhoneBook);

//        LinearLayoutManager manager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(manager);
//        Log.d(TAG, "contactInfoList ADAPTER: " + contactInfoList);
//        adapter = new BringPhoneBookAdapter(this, contactInfoList);
//        Log.d(TAG, "HERE HERE HERE");
//        recyclerView.setAdapter(adapter);

        Log.d(TAG, "HERE HERE HERE HERE HERE");

        btnUploadPhonebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayoutManager manager = new LinearLayoutManager(ContactPeopleActivityLogIn.this);
                manager.setOrientation(LinearLayoutManager.VERTICAL);
//                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(manager);
                Log.d(TAG, "contactInfoList ADAPTER: " + contactInfoList);
                adapter = new BringPhoneBookAdapter(ContactPeopleActivityLogIn.this, contactInfoList);
                adapter.setListener(ContactPeopleActivityLogIn.this);
                Log.d(TAG, "HERE HERE HERE");
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(adapter);

                Log.d(TAG, "HERE HERE HERE HERE HERE");
            }
        });


        btnCPText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickMethod = "Text";

                if(checkUser == true){
                    createContactPeople(clickMethod);
                }

                Intent intent = getPackageManager().getLaunchIntentForPackage("com.android.messaging");
//                intent.setData(Uri.parse("msg:" + userNamestr));
                startActivity(intent);

            }
        });

        btnCPCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clickMethod = "Call";
                if(checkUser == true){
                    createContactPeople(clickMethod);
                }

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + userPhoneNumberstr));
                startActivity(intent);
            }
        });
    }

    public void checkUser(){

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            userEmail = user.getEmail();
            Log.d(TAG, userEmail + "");
            Toast.makeText(ContactPeopleActivityLogIn.this, userEmail, Toast.LENGTH_SHORT).show();
        }
    }

    private void loadData() {
        dao.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot data : snapshot.getChildren()){

                    Log.d(TAG,"Load data");
                    HiDUserInformation hiDUserInformation = data.getValue(HiDUserInformation.class);
                    userInforList.add(hiDUserInformation);

                    if(userEmail.equals(hiDUserInformation.getUserEmail())) {
                        key1 = data.getKey();
                        checkUser = true;
                        Log.d(TAG, "key1: " + key1);

                        if(hiDUserInformation.getContactPeople() != null){
                            contactPeopleList = hiDUserInformation.getContactPeople();
                        }
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "Failed to load ContactPeople List");
            }
        });
    }

    private void createContactPeople(String clickMethod){

        today = Calendar.getInstance().getTime();
        dateFormat = new SimpleDateFormat("MM/dd/yy");
        todayDate = dateFormat.format(today);

        ContactPeople contactPeople = new ContactPeople(todayDate, userNamestr, clickMethod);
        contactPeopleList.add(contactPeople);

        dao.createContactPeople(key1, contactPeopleList).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(ContactPeopleActivityLogIn.this, "Save ContactPeople Success", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ContactPeopleActivityLogIn.this, "Update Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void bringContactInformation(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){

            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, 1);

        } else {
            getContactInfo();
        }

    }

    private void getContactInfo(){
        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null, null, null, null);

        while (cursor.moveToNext()){
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            @SuppressLint("Range") String phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            UserPhoneBook userPhoneBook = new UserPhoneBook(name, phoneNumber);
            Log.d(TAG, "userphoneBookName: " + userPhoneBook.getUserName());
            Log.d(TAG, "userphoneBookNumber: " + userPhoneBook.getUserPhoneNumber());
            contactInfoList.add(userPhoneBook);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getContactInfo();
            }
        }
    }

    @Override
    public void onListItemClick(int position) {

        UserPhoneBook userPhoneBook = contactInfoList.get(position);
        userNamestr = userPhoneBook.getUserName();
        userPhoneNumberstr = userPhoneBook.getUserPhoneNumber();

    }
}