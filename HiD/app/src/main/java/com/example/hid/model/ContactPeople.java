package com.example.hid.model;

public class ContactPeople {

    private String contactDate;
    private String method;

    public ContactPeople() {}

    public ContactPeople(String contactDate, String method) {
        this.contactDate = contactDate;
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
}
