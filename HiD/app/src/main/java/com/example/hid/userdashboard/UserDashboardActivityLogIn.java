package com.example.hid.userdashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hid.R;
import com.example.hid.activities.NavigationActivityLogIn;
import com.example.hid.dao.HiDUserInformationDAO;
import com.example.hid.databinding.ActivityUserDashboardBinding;
import com.example.hid.dforum.MyDiaryActivityLogIn;
import com.example.hid.model.BoxBreathing;
import com.example.hid.model.ContactPeople;
import com.example.hid.model.HiDUserInformation;
import com.example.hid.model.MyDDiary;
import com.example.hid.model.NotificationDBM;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDashboardActivityLogIn extends NavigationActivityLogIn {

    private static final String TAG = UserDashboardActivityLogIn.class.getSimpleName();
    ActivityUserDashboardBinding activityUserDashboardBinding;

    RecyclerView recyclerView;
    HiDUserInformationDAO dao;
    UserDashBoardAdapter adapter;

    String userEmail;
    ArrayList<HiDUserInformation> userInforList = new ArrayList<>();
    String key1;
    boolean checkUser = false;
    boolean checkDate = false;
    List<BoxBreathing> boxBreathingList = new ArrayList<>();
    List<ContactPeople> contactPeopleList = new ArrayList<>();
    List<NotificationDBM> notificationDBMList = new ArrayList<>();
    List<MyDDiary> myDDiaryList = new ArrayList<>();
    List<String> dateListAll = new ArrayList<>();
    int maxNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_user_dashboard);

        activityUserDashboardBinding = ActivityUserDashboardBinding.inflate(getLayoutInflater());
        View rootView = getLayoutInflater().inflate(R.layout.activity_user_dashboard, frameLayout);
        dao = new HiDUserInformationDAO();

        checkUser();
        loadData();

//        dateListAll = generateDateList();
//        Collections.sort(dateListAll);



//        Toast.makeText(UserDashboardActivityLogIn.this, maxNum, Toast.LENGTH_SHORT).show();

        recyclerView = rootView.findViewById(R.id.userDashBoardRecyclerView);
//        recyclerView.setHasFixedSize(true);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

//        adapter = new UserDashBoardAdapter(this, boxBreathingList,  contactPeopleList, notificationDBMList,  myDDiaryList, dateListAll, 5);
//        recyclerView.setAdapter(adapter);

    }

    public void checkUser(){

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            userEmail = user.getEmail();
            Log.d(TAG, userEmail + "");
            Toast.makeText(UserDashboardActivityLogIn.this, userEmail, Toast.LENGTH_SHORT).show();
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
                        Log.d(TAG, "getNotification value: " + hiDUserInformation.getNotificationDBM());
                        Log.d(TAG, "getBoxthing value: " + hiDUserInformation.getBoxBreathing());
                        Log.d(TAG, "getcontactPeople value: " + hiDUserInformation.getContactPeople());
                        Log.d(TAG, "getMyDDiary value: " + hiDUserInformation.getMyDDiary());

                        if(hiDUserInformation.getBoxBreathing() != null){
                            boxBreathingList = hiDUserInformation.getBoxBreathing();
                        }

                        if(hiDUserInformation.getContactPeople() != null){
                            contactPeopleList = hiDUserInformation.getContactPeople();
                        }

                        if(hiDUserInformation.getNotificationDBM() != null){
                            notificationDBMList = hiDUserInformation.getNotificationDBM();
                        }

                        if(hiDUserInformation.getMyDDiary() != null){
                            myDDiaryList = hiDUserInformation.getMyDDiary();
                        }
                    }
                }
                dateListAll = generateDateList();
                Collections.sort(dateListAll);
                for(String dateList : dateListAll){
                    Log.d(TAG, "Date: " + dateList);
                }

                maxNum = returnMaxSize(boxBreathingList,  contactPeopleList, notificationDBMList,  myDDiaryList);
                Log.d(TAG, "MaaNum: " + maxNum);
                adapter = new UserDashBoardAdapter(UserDashboardActivityLogIn.this, boxBreathingList,  contactPeopleList, notificationDBMList,  myDDiaryList, dateListAll, maxNum);
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "Failed to load MyDDiary List");
            }
        });
    }

    private int returnMaxSize(List<BoxBreathing> boxBreathingList, List<ContactPeople> contactPeopleList, List<NotificationDBM>  notificationDBMList, List<MyDDiary> myDDiaryList){

        int boxBeathSize = boxBreathingList.size();
        int contactPSize = contactPeopleList.size();
        int notificationSize = notificationDBMList.size();
        int myDDiarySize = myDDiaryList.size();

        List<Integer> sizeList = new ArrayList<>();
        sizeList.add(boxBeathSize);
        sizeList.add(contactPSize);
        sizeList.add(notificationSize);
        sizeList.add(myDDiarySize);

        Collections.sort(sizeList);


        return sizeList.get(sizeList.size() - 1);
    }

    public List<String> generateDateList(){
        //boxBreathingList
        //contactPeopleList
        //notificationDBMList
        //myDDiaryList
        List<String> dateList = new ArrayList<>();

        for(BoxBreathing boxBreathing : boxBreathingList){
            checkDate = dateList.contains(boxBreathing.getBreathDate());
            if(!checkDate){
                dateList.add(boxBreathing.getBreathDate());
            }
        }

        for(ContactPeople contactPeople : contactPeopleList){
            checkDate = dateList.contains(contactPeople.getContactDate());
            if(!checkDate){
                dateList.add(contactPeople.getContactDate());
            }
        }

        for(NotificationDBM notificationDBM : notificationDBMList){
            checkDate = dateList.contains(notificationDBM.getNotifiDate());
            if(!checkDate){
                dateList.add(notificationDBM.getNotifiDate());
            }
        }

        for(MyDDiary myDDiary : myDDiaryList){
            checkDate = dateList.contains(myDDiary.getMyDDate());
            if(!checkDate){
                dateList.add(myDDiary.getMyDDate());
            }
        }

        return dateList;

    }
}