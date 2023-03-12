package com.example.hid.model;

import java.util.List;

public class HiDUserInformation {

    private String userEmail;
    private String userFirstName;
    private String userLastName;
    private List<BoxBreathing> BoxBreathing;
    private List<NotificationDBM> NotificationDBM;
//    private NotificationDBM NotificationDBM;
    private List<CreateMyD> CreateMyD;
    private List<ContactPeople> ContactPeople;

    public HiDUserInformation() {}

//    public HiDUserInformation(String userEmail, String userFirstName, String userLastName, List<com.example.hid.model.BoxBreathing> boxBreathing) {
//        this.userEmail = userEmail;
//        this.userFirstName = userFirstName;
//        this.userLastName = userLastName;
//        BoxBreathing = boxBreathing;
//    }

    public HiDUserInformation(String userEmail, String userFirstName, String userLastName, List<com.example.hid.model.NotificationDBM> notificationDBM) {
        this.userEmail = userEmail;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        NotificationDBM = notificationDBM;
    }

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

    public HiDUserInformation(String userEmail, String userFirstName, String userLastName, List<com.example.hid.model.BoxBreathing> boxBreathing, List<com.example.hid.model.NotificationDBM> notificationDBM, List<com.example.hid.model.CreateMyD> createMyD, List<com.example.hid.model.ContactPeople> contactPeople) {
        this.userEmail = userEmail;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        BoxBreathing = boxBreathing;
        NotificationDBM = notificationDBM;
        CreateMyD = createMyD;
        ContactPeople = contactPeople;
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

//    public List<com.example.hid.model.BoxBreathing> getBoxBreathing() {
//        return BoxBreathing;
//    }
//
//    public void setBoxBreathing(List<com.example.hid.model.BoxBreathing> boxBreathing) {
//        BoxBreathing = boxBreathing;
//    }

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
//
//    public List<com.example.hid.model.ContactPeople> getContactPeople() {
//        return ContactPeople;
//    }
//
//    public void setContactPeople(List<com.example.hid.model.ContactPeople> contactPeople) {
//        ContactPeople = contactPeople;
//    }
}
