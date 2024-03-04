package com.example.meidiamusic.model;

import java.io.Serializable;

public class TuyenTap implements Serializable {
    int imageTT;
    String nameTT;

    public TuyenTap() {
    }

    public TuyenTap(int imageTT, String nameTT) {
        this.imageTT = imageTT;
        this.nameTT = nameTT;
    }

    public int getImageTT() {
        return imageTT;
    }

    public void setImageTT(int imageTT) {
        this.imageTT = imageTT;
    }

    public String getNameTT() {
        return nameTT;
    }

    public void setNameTT(String nameTT) {
        this.nameTT = nameTT;
    }
}
