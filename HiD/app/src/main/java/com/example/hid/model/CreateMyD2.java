package com.example.hid.model;

public class CreateMyD2 {

    private String createDdate;
    private int myDImage;
    private boolean share;

    public CreateMyD2() {}

    public CreateMyD2(String createDdate, int myDImage, boolean share) {
        this.createDdate = createDdate;
        this.myDImage = myDImage;
        this.share = share;
    }

    public String getCreateDdate() {
        return createDdate;
    }

    public void setCreateDdate(String createDdate) {
        this.createDdate = createDdate;
    }

    public int getMyDImage() {
        return myDImage;
    }

    public void setMyDImage(int myDImage) {
        this.myDImage = myDImage;
    }

    public boolean isShare() {
        return share;
    }

    public void setShare(boolean share) {
        this.share = share;
    }
}
