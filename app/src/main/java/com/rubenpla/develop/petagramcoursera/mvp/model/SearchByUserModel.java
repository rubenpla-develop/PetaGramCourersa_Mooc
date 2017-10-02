package com.rubenpla.develop.petagramcoursera.mvp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class SearchByUserModel implements Parcelable{

    private String userName;
    private String userId;
    private String urlAvatar;

    protected SearchByUserModel(Parcel in) {
        userName = in.readString();
        userId = in.readString();
        urlAvatar = in.readString();
    }

    public SearchByUserModel(){}

    public static final Creator<SearchByUserModel> CREATOR = new Creator<SearchByUserModel>() {
        @Override
        public SearchByUserModel createFromParcel(Parcel in) {
            return new SearchByUserModel(in);
        }

        @Override
        public SearchByUserModel[] newArray(int size) {
            return new SearchByUserModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userName);
        dest.writeString(userId);
        dest.writeString(urlAvatar);
    }

    @Override
    public String toString() {
        return "SearchByUserModel{" +
                "userName='" + userName + '\'' +
                ", userId='" + userId + '\'' +
                ", urlAvatar='" + urlAvatar + '\'' +
                '}';
    }

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

    public static Creator<SearchByUserModel> getCREATOR() {
        return CREATOR;
    }
}
