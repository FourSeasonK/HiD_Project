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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hid.R;
import com.example.hid.activities.NavigationActivity;
import com.example.hid.contactpeople.adapter.BringPhoneBookAdapter;
import com.example.hid.databinding.ActivityContactPeopleBinding;
import com.example.hid.model.UserPhoneBook;

import java.util.ArrayList;

public class ContactPeopleActivity extends NavigationActivity implements BringPhoneBookAdapter.ListItemListener{

    private static final String TAG = ContactPeopleActivity.class.getSimpleName();
    ActivityContactPeopleBinding activityContactPeopleBinding;

    private Button btnCPText, btnCPCall, btnUploadPhonebook;

    RecyclerView recyclerView;
    BringPhoneBookAdapter adapter;
    ArrayList<UserPhoneBook> contactInfoList = new ArrayList<>();
    String userNamestr, userPhoneNumberstr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_contact_people);

        activityContactPeopleBinding = ActivityContactPeopleBinding.inflate(getLayoutInflater());
        View rootView = getLayoutInflater().inflate(R.layout.activity_contact_people, frameLayout);

        bringContactInformation();

        recyclerView = rootView.findViewById(R.id.recycler_phoneBook);
        btnCPText = rootView.findViewById(R.id.btnCPText);
        btnCPCall = rootView.findViewById(R.id.btnCPCall);
        btnUploadPhonebook = rootView.findViewById(R.id.btnUploadPhoneBook);

        btnUploadPhonebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayoutManager manager = new LinearLayoutManager(ContactPeopleActivity.this);
                manager.setOrientation(LinearLayoutManager.VERTICAL);
//                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(manager);
                Log.d(TAG, "contactInfoList ADAPTER: " + contactInfoList);
                adapter = new BringPhoneBookAdapter(ContactPeopleActivity.this, contactInfoList);
                adapter.setListener(ContactPeopleActivity.this);
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(adapter);
            }
        });

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
                intent.setData(Uri.parse("tel:" + userPhoneNumberstr));
                startActivity(intent);
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