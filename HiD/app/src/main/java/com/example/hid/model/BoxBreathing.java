package com.example.hid.model;

public class BoxBreathing {

    private String breathDate;
    private int breathNumTimes;

    public BoxBreathing() {}

    public BoxBreathing(String breathDate, int breathNumTimes) {
        this.breathDate = breathDate;
        this.breathNumTimes = breathNumTimes;
    }

    public String getBreathDate() {
        return breathDate;
    }

    public void setBreathDate(String breathDate) {
        this.breathDate = breathDate;
    }

    public int getBreathNumTimes() {
        return breathNumTimes;
    }

    public void setBreathNumTimes(int breathNumTimes) {
        this.breathNumTimes = breathNumTimes;
    }
}
