package com.example.hid.model;

public class CreateMyD {

    private String title;
    private String myDName;
    private String myDDate;
    private String myMood;
    private String myDContents;
    private String myDImageUrl;

    public CreateMyD(){}

    public CreateMyD(String title, String myDName, String myDDate, String myMood, String myDContents, String myDImageUrl) {
        if(title.trim().equals("")){
            title = "No Title";
        }

        this.title = title;
        this.myDDate = myDDate;
        this.myMood = myMood;
        this.myDContents = myDContents;
        this.myDImageUrl = myDImageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMyDName() {
        return myDName;
    }

    public void setMyDName(String myDName) {
        this.myDName = myDName;
    }

    public String getMyDDate() {
        return myDDate;
    }

    public void setMyDDate(String myDDate) {
        this.myDDate = myDDate;
    }

    public String getMyMood() {
        return myMood;
    }

    public void setMyMood(String myMood) {
        this.myMood = myMood;
    }

    public String getMyDContents() {
        return myDContents;
    }

    public void setMyDContents(String myDContents) {
        this.myDContents = myDContents;
    }

    public String getMyDImageUrl() {
        return myDImageUrl;
    }

    public void setMyDImageUrl(String myDImageUrl) {
        this.myDImageUrl = myDImageUrl;
    }
}
