package com.example.hid.model;

public class NotificationDBM {

    private String notifiDate;
    private String situation;
    private String solution;

    public NotificationDBM() {}

    public NotificationDBM(String notifiDate, String situation, String solution) {
        this.notifiDate = notifiDate;
        this.situation = situation;
        this.solution = solution;
    }

    public String getNotifiDate() {
        return notifiDate;
    }

    public void setNotifiDate(String notifiDate) {
        this.notifiDate = notifiDate;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }
}
