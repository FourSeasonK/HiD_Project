package com.example.hid.model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HiDUserInformation {

    private String userEmail;
    private String userFirstName;
    private String userLastName;
    private List<BoxBreathing> BoxBreathing;
    private List<NotificationDBM> NotificationDBM;
//    private NotificationDBM NotificationDBM;
    private List<CreateMyD> CreateMyD;
    private List<ContactPeople> ContactPeople;
    private List<MyDDiary> MyDDiary;

    public HiDUserInformation() {}

//    public HiDUserInformation(String userEmail, String userFirstName, String userLastName, List<com.example.hid.model.NotificationDBM> notificationDBM) {
//        this.userEmail = userEmail;
//        this.userFirstName = userFirstName;
//        this.userLastName = userLastName;
//        NotificationDBM = notificationDBM;
//    }

    //    public HiDUserInformation(String userEmail, String userFirstName, String userLastName, List<com.example.hid.model.BoxBreathing> boxBreathing) {
//        this.userEmail = userEmail;
//        this.userFirstName = userFirstName;
//        this.userLastName = userLastName;
//        BoxBreathing = boxBreathing;
//    }

//    public HiDUserInformation(String userEmail, String userFirstName, String userLastName, List<com.example.hid.model.CreateMyD> createMyD) {
//        this.userEmail = userEmail;
//        this.userFirstName = userFirstName;
//        this.userLastName = userLastName;
//        CreateMyD = createMyD;
//    }
//
//    public HiDUserInformation(String userEmail, String userFirstName, String userLastName, List<com.example.hid.model.ContactPeople> contactPeople) {
//        this.userEmail = userEmail;
//        this.userFirstName = userFirstName;
//        this.userLastName = userLastName;
//        ContactPeople = contactPeople;
//    }

//    public HiDUserInformation(String userEmail, String userFirstName, String userLastName, List<com.example.hid.model.BoxBreathing> boxBreathing, List<com.example.hid.model.NotificationDBM> notificationDBM, List<com.example.hid.model.CreateMyD> createMyD, List<com.example.hid.model.ContactPeople> contactPeople) {
//        this.userEmail = userEmail;
//        this.userFirstName = userFirstName;
//        this.userLastName = userLastName;
//        BoxBreathing = boxBreathing;
//        NotificationDBM = notificationDBM;
//        CreateMyD = createMyD;
//        ContactPeople = contactPeople;
//    }


    public HiDUserInformation(String userEmail, String userFirstName, String userLastName) {
        this.userEmail = userEmail;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("userEmail", userEmail);
        result.put("userFirstName", userFirstName);
        result.put("userLastName", userLastName);

        return result;
    }

    public HiDUserInformation(String userEmail, String userFirstName, String userLastName, List<com.example.hid.model.BoxBreathing> boxBreathing, List<com.example.hid.model.NotificationDBM> notificationDBM, List<com.example.hid.model.ContactPeople> contactPeople, List<MyDDiary> myDDiary) {
        this.userEmail = userEmail;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        BoxBreathing = boxBreathing;
        NotificationDBM = notificationDBM;
        ContactPeople = contactPeople;
        MyDDiary = myDDiary;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public List<com.example.hid.model.BoxBreathing> getBoxBreathing() {
        return BoxBreathing;
    }

    public void setBoxBreathing(List<com.example.hid.model.BoxBreathing> boxBreathing) {
        BoxBreathing = boxBreathing;
    }

    public List<com.example.hid.model.NotificationDBM> getNotificationDBM() {
        return NotificationDBM;
    }

    public void setNotificationDBM(List<com.example.hid.model.NotificationDBM> notificationDBM) {
        NotificationDBM = notificationDBM;
    }


//    public List<com.example.hid.model.CreateMyD> getCreateMyD() {
//        return CreateMyD;
//    }
//
//    public void setCreateMyD(List<com.example.hid.model.CreateMyD> createMyD) {
//        CreateMyD = createMyD;
//    }

    public List<com.example.hid.model.ContactPeople> getContactPeople() {
        return ContactPeople;
    }

    public void setContactPeople(List<com.example.hid.model.ContactPeople> contactPeople) {
        ContactPeople = contactPeople;
    }

    public List<com.example.hid.model.MyDDiary> getMyDDiary() {
        return MyDDiary;
    }

    public void setMyDDiary(List<com.example.hid.model.MyDDiary> myDDiary) {
        MyDDiary = myDDiary;
    }
}
