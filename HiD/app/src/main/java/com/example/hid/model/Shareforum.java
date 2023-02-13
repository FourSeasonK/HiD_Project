package com.example.hid.model;

import java.util.Date;

public class Shareforum {
    private String number;
    private String title;
    private String name;
    private String dateToday;
    private int sfHeart;
    private int sfStar;

    public Shareforum() {}

    public Shareforum(String number, String title, String name, String dateToday, int sfHeart, int sfStar) {
        this.number = number;
        this.title = title;
        this.name = name;
        this.dateToday = dateToday;
        this.sfHeart = sfHeart;
        this.sfStar = sfStar;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateToday() {
        return dateToday;
    }

    public void setDateToday(String dateToday) {
        this.dateToday = dateToday;
    }

    public int getSfHeart() {
        return sfHeart;
    }

    public void setSfHeart(int sfHeart) {
        this.sfHeart = sfHeart;
    }

    public int getSfStar() {
        return sfStar;
    }

    public void setSfStar(int sfStar) {
        this.sfStar = sfStar;
    }
}
