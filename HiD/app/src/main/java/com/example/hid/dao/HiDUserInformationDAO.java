package com.example.hid.dao;

import com.example.hid.model.BoxBreathing;
import com.example.hid.model.ContactPeople;
import com.example.hid.model.HiDUserInformation;
import com.example.hid.model.MyDDiary;
import com.example.hid.model.NotificationDBM;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

//    public Task<Void> createNotificationAsList(String key, List<NotificationDBM> notificationDBM){
    public Task<Void> createNotificationAsList(String key, List<NotificationDBM> notificationDBM){

//        return dbReference.push().setValue(notificationDBMAsList);
        return dbReference.child(key).child("NotificationDBM").setValue(notificationDBM);
    }

    public Query get(){
        return dbReference;
    }

    public Task<Void> updateNotification(String key,  String notification, HashMap<String, Object> hashMap){
        return dbReference.child(key).child(notification).updateChildren(hashMap);
    }

    public Task<Void> updateUserInformation(String key, HashMap<String, Object> hashMap){
        return dbReference.child(key).updateChildren(hashMap);
    }

    public Task<Void> remove(String key, String notification) {
//    public Task<Void> remove(String notification) {

        return dbReference.child(key).child(notification).removeValue();
    }

    public Task<Void> createBoxBreathing(String key, List<BoxBreathing> boxBreathings){
        return dbReference.child(key).child("BoxBreathing").setValue(boxBreathings);
    }

    public Task<Void> createContactPeople(String key, List<ContactPeople> contactPeoples){
        return dbReference.child(key).child("ContactPeople").setValue(contactPeoples);
    }

    public Task<Void> createMyDDiary(String key, List<MyDDiary> myDDiaries){
        return dbReference.child(key).child("MyDDiary").setValue(myDDiaries);
    }

    public Task<Void> deleteUserInformation(String key){
        return dbReference.child(key).removeValue();
    }

//    public Task<Void> updateUserInformation(String key, String userEmail, String userFirstName, String userLastName){
//
//        //String key = dbReference.child(id).push().getKey();
//        HiDUserInformation hiDUserInformation = new HiDUserInformation(userEmail, userFirstName, userLastName);
//
//        Map<String, Object> userInfoValue = hiDUserInformation.toMap();
//
//        Map<String, Object> userInfoUpdate = new HashMap<>();
//        userInfoUpdate.put(key, userInfoUpdate);
//
//        return dbReference.
//    }

}
