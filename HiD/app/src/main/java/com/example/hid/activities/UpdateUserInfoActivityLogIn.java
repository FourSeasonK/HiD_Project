package com.example.hid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.hid.R;
import com.example.hid.dao.HiDUserInformationDAO;
import com.example.hid.databinding.ActivityUpdateUserinfoBinding;
import com.example.hid.dialog.DeleteUserDialog;
import com.example.hid.model.HiDUserInformation;
import com.example.hid.dialog.UpdateUserInfoDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UpdateUserInfoActivityLogIn extends NavigationActivityLogIn implements UpdateUserInfoDialog.UpdateUserInforDialogListener, DeleteUserDialog.DeleteUserInfoDialogListener {

    private static final String TAG = UpdateUserInfoActivityLogIn.class.getSimpleName();
    ActivityUpdateUserinfoBinding activityUpdateUserinfoBinding;

    private TextInputLayout UPU_input_layout_email, UPU_input_layout_password;
    private EditText firstName, lastName, email, password, confirmPassword;
    private ProgressBar progressBar;
    private Button btnUpdate, btnDeleteUser;
    private TextView txtTitle;
    String strFirstName, strLastName, strEmail, strPassword;
    private boolean logIn = false;

    private FirebaseAuth mAuth;
    HiDUserInformationDAO dao;
    String userEmail;
//    ArrayList<HiDUserInformation> userInforList = new ArrayList<>();
    String key1 , userFirstNameDB, userLastNameDB;
    boolean checkUser, checkClickOK = false;
    List<HiDUserInformation> onlyUserInfoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_create_account);

        activityUpdateUserinfoBinding = ActivityUpdateUserinfoBinding.inflate(getLayoutInflater());
        View rootView = getLayoutInflater().inflate(R.layout.activity_update_userinfo, frameLayout);

//        mAuth = FirebaseAuth.getInstance();
        dao = new HiDUserInformationDAO();
        checkUser();
        loadData();

        txtTitle = rootView.findViewById(R.id.txtUpdateUserInfoTitle);
        txtTitle.setText(userEmail);

        firstName = rootView.findViewById(R.id.edittxtUPUFirstName);
        lastName = rootView.findViewById(R.id.edittxtUPULastName);
        UPU_input_layout_email = rootView.findViewById(R.id.UPU_input_layout_email);
        email = rootView.findViewById(R.id.edittxtUPUEmail);
        UPU_input_layout_password = rootView.findViewById(R.id.UPU_input_layout_password);
        password = rootView.findViewById(R.id.edittxtUPUPassword);
//        CA_input_layout_confirmpassword = rootView.findViewById(R.id.CAConfirm_input_layout_password);
//        confirmPassword = rootView.findViewById(R.id.edittxtCAConPassword);
        btnUpdate = rootView.findViewById(R.id.btnUPUUpdate);
        btnDeleteUser = rootView.findViewById(R.id.btnUPUDeleteUser);
//        progressBar = rootView.findViewById(R.id.progressBar);


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                strFirstName = firstName.getText().toString();
                strLastName = lastName.getText().toString();
                strEmail = email.getText().toString();
//        strPassword = password.getText().toString().trim();
                Log.d(TAG, "userEmail: " + strEmail);
                Log.d(TAG, "userFirstName: " + strFirstName);
                Log.d(TAG, "userLastName: "+ strLastName);

                openUpdateUserInfoDialog(strFirstName, strLastName, strEmail);

            }
        });

        btnDeleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openDeleteUserInfoDialog();
            }
        });

    }

    public void onUpdateUserInfo(String key, HashMap<String, Object> hashMap){

        dao.updateUserInformation(key, hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getApplicationContext(), "Update Successful", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Update is failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onDeleteUserInfo(String key){

        dao.deleteUserInformation(key).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d(TAG, "Delete User Successful");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "Delete User information is failed: " + e.getMessage());
            }
        });
    }

    public void updateUserEmailInAuth(String email){

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.updateEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Log.d(TAG, "User email address updated.");
                }
            }
        });
    }

    public void checkUser(){

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            userEmail = user.getEmail();
            Log.d(TAG, userEmail + "");
            //Toast.makeText(UpdateUserInfoActivityLogIn.this, userEmail, Toast.LENGTH_SHORT).show();
        }
    }

    private void loadData() {
        dao.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot data : snapshot.getChildren()){

                    Log.d(TAG,"Load data");
                    //Toast.makeText(UpdateUserInfoActivityLogIn.this, "Load data", Toast.LENGTH_SHORT).show();
                    HiDUserInformation hiDUserInformation = data.getValue(HiDUserInformation.class);
//                    userInforList.add(hiDUserInformation);

                    if(userEmail.equals(hiDUserInformation.getUserEmail())) {
                        key1 = data.getKey();
                        checkUser = true;
                        userFirstNameDB = hiDUserInformation.getUserFirstName();
                        userLastNameDB = hiDUserInformation.getUserLastName();
                        HiDUserInformation  hiDOnlyUserInfo = new HiDUserInformation(hiDUserInformation.getUserEmail(), userFirstNameDB, userLastNameDB);
                        onlyUserInfoList.add(hiDOnlyUserInfo);
                        //Toast.makeText(UpdateUserInfoActivityLogIn.this, "key1: " + key1, Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "key1: " + key1);

//                        if(hiDUserInformation.getContactPeople() != null){
//                            contactPeopleList = hiDUserInformation.getContactPeople();
//                        }
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "Failed to load ContactPeople List");
            }
        });
    }

    public void openUpdateUserInfoDialog(String firstName, String lastName, String email){
        UpdateUserInfoDialog updateUserInfoDialog = new UpdateUserInfoDialog(firstName, lastName, email);
        updateUserInfoDialog.show(getSupportFragmentManager(), "Update User Information");
    }

    public void openDeleteUserInfoDialog(){
        DeleteUserDialog deleteUserDialog = new DeleteUserDialog();
        deleteUserDialog.show(getSupportFragmentManager(), "Delete User Information");
    }


    @Override
    public void displayTexts() {
//    public void displayTexts(String firstName, String lastName, String email) {
        strFirstName = firstName.getText().toString();
        strLastName = lastName.getText().toString();
        strEmail = email.getText().toString();

//        HashMap<String, Object> hashMap = new HashMap<>();
//        hashMap.put("userEmail", firstName);
//        hashMap.put("userFirstName", lastName);
//        hashMap.put("userLastName", email);

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("userEmail", strEmail);
        hashMap.put("userFirstName", strFirstName);
        hashMap.put("userLastName", strLastName);

        onUpdateUserInfo(key1, hashMap);
        updateUserEmailInAuth(strEmail);
//        updateUserEmailInAuth(email);

        Intent intent = new Intent(UpdateUserInfoActivityLogIn.this, HomeActivityLogInD.class);
        startActivity(intent);
    }

    @Override
    public void deleteConfirmMessage() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){
                    Toast.makeText(UpdateUserInfoActivityLogIn.this, "User account deleted", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(UpdateUserInfoActivityLogIn.this, LogInOutActivity.class);
                    startActivity(intent);

                }
            }
        });

        onDeleteUserInfo(key1);
    }
}