package com.rubenpla.develop.petagramcoursera.mvp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ProfileInfo implements Parcelable {
    private String userName;
    private String userId;
    private String urlAvatar;

    public ProfileInfo(Parcel in) {
        userName = in.readString();
        userId = in.readString();
        urlAvatar = in.readString();
    }

    public ProfileInfo() {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userName);
        dest.writeString(userId);
        dest.writeString(urlAvatar);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ProfileInfo> CREATOR = new Creator<ProfileInfo>() {
        @Override
        public ProfileInfo createFromParcel(Parcel in) {
            return new ProfileInfo(in);
        }

        @Override
        public ProfileInfo[] newArray(int size) {
            return new ProfileInfo[size];
        }
    };

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUrlAvatar() {
        return urlAvatar;
    }

    public void setUrlAvatar(String urlAvatar) {
        this.urlAvatar = urlAvatar;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
