package com.example.hid.dforum;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.hid.R;
import com.example.hid.activities.HomeActivityLogInD;
import com.example.hid.activities.NavigationActivityLogIn;
import com.example.hid.boxbreath.BoxBreathingActivityLogIn;
import com.example.hid.created.CreateMyDActivity;
import com.example.hid.dao.HiDUserInformationDAO;
import com.example.hid.databinding.ActivityUpdateDforumBinding;
import com.example.hid.dialog.MyDDiaryDialog;
import com.example.hid.model.HiDUserInformation;
import com.example.hid.model.MyDDiary;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MyDiaryActivityLogIn extends NavigationActivityLogIn {
//public class MyDiaryActivityLogIn extends NavigationActivityLogIn implements MyDDiaryDialog.DoWriteDDiaryDialogListener {

    private static final String TAG = MyDiaryActivityLogIn.class.getSimpleName();
    private static final int PICK_IMAGE_REQUEST = 1;
    ActivityUpdateDforumBinding activityUpdateDforumBinding;

    EditText myDTitle, myDDName, myDMood, myDContents;
    ImageView myDImage;
    Button btnUploasImg, btnSave;

    Date today;
    SimpleDateFormat dateFormat;
    String todayDate;
    private Uri mImgUri;

    HiDUserInformationDAO dao;
    String userEmail;
    ArrayList<HiDUserInformation> userInforList = new ArrayList<>();
    String key1;
    boolean checkUser = false;
    List<MyDDiary> myDDiaryList = new ArrayList<>();

    private StorageReference mStorageRef;
    //private DatabaseReference mDatabaseRef;
    private StorageTask mSaveTask;
    boolean doNotShowAgian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_update_dforum);

        activityUpdateDforumBinding = ActivityUpdateDforumBinding.inflate(getLayoutInflater());
        View rootView = getLayoutInflater().inflate(R.layout.activity_mydiarylogin, frameLayout);

        dao = new HiDUserInformationDAO();
        checkUser();
        loadData();

        myDTitle = rootView.findViewById(R.id.txtMyDTitle);
        myDDName = rootView.findViewById(R.id.txtMyDName);
        myDMood = rootView.findViewById(R.id.txtMyDMood);
        myDContents = rootView.findViewById(R.id.edittxtMyDContents);
        myDImage = rootView.findViewById(R.id.MyDImg);

        btnUploasImg = rootView.findViewById(R.id.btnMyDUpload);
        btnSave = rootView.findViewById(R.id.btnMyDSave);

        /////To Test the App/////
//        removeDataFromPref(MyDiaryActivityLogIn.this);
        openMyDDiaryDialog();

        myDImage.setVisibility(View.VISIBLE);


        mStorageRef = FirebaseStorage.getInstance().getReference("myDdiary");
        //mDatabaseRef = FirebaseDatabase.getInstance().getReference("myDdiary");


        btnUploasImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = myDTitle.getText().toString();
                String myDname = myDDName.getText().toString().trim();
                String mood = myDMood.getText().toString();
                String contents = myDContents.getText().toString();

                if(title.equals("") || myDname.equals("") || mood.equals("") || contents.equals("")){
                    Toast.makeText(MyDiaryActivityLogIn.this, "Please type empty fields", Toast.LENGTH_SHORT).show();

                } else {
                    if(mSaveTask != null && mSaveTask.isInProgress()){
                        Toast.makeText(MyDiaryActivityLogIn.this, "Save in progress", Toast.LENGTH_SHORT).show();
                    } else {
                        saveFileToFirebase(myDname);
                    }
                }
            }
        });
    }

    private void openFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //when User get the actual image
//        Log.d(TAG, "requestCode: " + requestCode);
//        Log.d(TAG, "resultCode: " + resultCode);
//        Log.d(TAG, "data: " + data);
//        Log.d(TAG, "data.getData(): " + data.getData());
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

                mImgUri = data.getData();
                Log.d(TAG, "get Image URI: " + mImgUri);
                Picasso.get().load(mImgUri).into(myDImage);
                //myDImage.setImageURI(mImgUri);
                //myDImage.setVisibility(View.VISIBLE);
        }
    }

    //get file extension from the image
    private String getFileExtension(Uri uri){
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void saveFileToFirebase(String myDname){

        String trimNoSpaceDname = myDname.trim().replace(" ", "");

        if(mImgUri != null){
            StorageReference fileReference = mStorageRef.child(trimNoSpaceDname + "." + getFileExtension(mImgUri));

            mSaveTask = fileReference.putFile(mImgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                    while (!urlTask.isSuccessful());
                    Uri downloadUrl = urlTask.getResult();

                    final String sdownload_url = String.valueOf(downloadUrl);

                    today = Calendar.getInstance().getTime();
                    dateFormat = new SimpleDateFormat("MM/dd/yy");
                    todayDate = dateFormat.format(today);

                    String strTitle = myDTitle.getText().toString();
                    String strMood = myDMood.getText().toString();
                    String strContents = myDContents.getText().toString();
                    String myDname = myDDName.getText().toString();

                    MyDDiary myDDiary = new MyDDiary(strTitle, myDname, todayDate, strMood, strContents, sdownload_url);
                    myDDiaryList.add(myDDiary);

                    if(checkUser == true) {
                        createMyDDiary(myDDiaryList);
                    }

                    myDTitle.setText("");
                    myDDName.setText("");
                    myDMood.setText("");
                    myDContents.setText("");
                    myDImage.setImageResource(0);

                    Toast.makeText(MyDiaryActivityLogIn.this, "Save Successful", Toast.LENGTH_SHORT).show();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MyDiaryActivityLogIn.this, e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }

    public void checkUser(){

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            userEmail = user.getEmail();
            Log.d(TAG, userEmail + "");
            Toast.makeText(MyDiaryActivityLogIn.this, userEmail, Toast.LENGTH_SHORT).show();
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
//                        Log.d(TAG, "getNotification value: " + hiDUserInformation.getNotificationDBM());
//                        Log.d(TAG, "getBoxthing value: " + hiDUserInformation.getBoxBreathing());
//                        Log.d(TAG, "getcontactPeople value: " + hiDUserInformation.getContactPeople());
//                        Log.d(TAG, "getMyDDiary value: " + hiDUserInformation.getMyDDiary());

                        if(hiDUserInformation.getMyDDiary() != null){
                            myDDiaryList = hiDUserInformation.getMyDDiary();
                            Log.d(TAG, "myDDiaryList: " + myDDiaryList);
                        }
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "Failed to load MyDDiary List");
            }
        });
    }

    private void createMyDDiary(List<MyDDiary> myDDiaryList){


        dao.createMyDDiary(key1, myDDiaryList).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(MyDiaryActivityLogIn.this, "Save MyD Diary Successful", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MyDiaryActivityLogIn.this, "Update Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void openMyDDiaryDialog(){
//        MyDDiaryDialog myDDiaryDialog = new MyDDiaryDialog();
//        myDDiaryDialog.show(getSupportFragmentManager(), "Start My D Diary");

        final AlertDialog.Builder adb = new AlertDialog.Builder(MyDiaryActivityLogIn.this);
        LayoutInflater adbInflater = LayoutInflater.from(MyDiaryActivityLogIn.this);
        View eulaLayout = adbInflater.inflate(R.layout.layout_myddiary_dialog, null);

        adb.setView(eulaLayout);
        adb.setTitle("Write Your Depression Diary");
        adb.setMessage("\nRecord your daily mood along with the depression image you created");
        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });

        adb.setNegativeButton("Do not show again", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                doNotShowAgian = true;
                SharedPreferences settings = getSharedPreferences("MyDDiaryLogIn", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean("skipMessage", doNotShowAgian);
                editor.commit();
                dialog.cancel();
            }
        });
        SharedPreferences settings = getSharedPreferences("MyDDiaryLogIn", 0);
        Boolean skipMessage = settings.getBoolean("skipMessage", false);

        if (skipMessage.equals(false)) {
            adb.show();

        }
    }

    //to test the app
    public static void removeDataFromPref(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyDDiaryLogIn", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("skipMessage");
        editor.commit();
    }

//    @Override
//    public void startDDiary(boolean checkCancel) {
//
//        if(checkCancel){
//            Intent intent = new Intent(MyDiaryActivityLogIn.this, HomeActivityLogInD.class);
//            startActivity(intent);
//        }
//    }
}