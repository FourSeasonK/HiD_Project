package com.example.hid.model;

public class ContactPeople {

    private String contactDate;
    private String receiverName;
    private String method;

    public ContactPeople() {}

    public ContactPeople(String contactDate, String receiverName, String method) {
        this.contactDate = contactDate;
        this.receiverName = receiverName;
        this.method = method;
    }

    public String getContactDate() {
        return contactDate;
    }

    public void setContactDate(String contactDate) {
        this.contactDate = contactDate;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }
}
