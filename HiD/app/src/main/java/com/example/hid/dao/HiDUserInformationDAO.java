package com.example.hid.dao;

import com.example.hid.model.HiDUserInformation;
import com.example.hid.model.NotificationDBM;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class HiDUserInformationDAO {

    private DatabaseReference dbReference;

    public HiDUserInformationDAO(){

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        dbReference = db.getReference(HiDUserInformation.class.getSimpleName());
    }

    public Task<Void> createHiDUserInformation(HiDUserInformation hiDUserInformation){

        return dbReference.push().setValue(hiDUserInformation);
    }

    public Task<Void> createNotification(String key, String notification, NotificationDBM notificationDBM){
//    public Task<Void> createNotification(String key, NotificationDBM notificationDBM){

        return dbReference.child(key).child(notification).push().setValue(notificationDBM);
//        return dbReference.child(key).push().setValue(notificationDBM);
    }

    public Query get(){
        return dbReference;
    }

    public Task<Void> update(String key,  String notification, HashMap<String, Object> hashMap){
        return dbReference.child(key).child(notification).updateChildren(hashMap);
    }


    public Task<Void> remove(String key, String notification) {

        return dbReference.child(key).child(notification).removeValue();
    }

}
