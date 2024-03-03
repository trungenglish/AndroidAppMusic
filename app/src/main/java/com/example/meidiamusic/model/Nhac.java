package com.example.meidiamusic.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Nhac implements Parcelable {
    int image;
    String title;

    public Nhac() {
    }

    public Nhac(int image, String title) {
        this.image = image;
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    protected Nhac(Parcel in) {
        image = Integer.parseInt(in.readString());
        title = in.readString();
    }

    public static final Creator<Nhac> CREATOR = new Creator<Nhac>() {
        @Override
        public Nhac createFromParcel(Parcel in) {
            return new Nhac(in);
        }

        @Override
        public Nhac[] newArray(int size) {
            return new Nhac[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(image);
        dest.writeString(title);
    }
}
