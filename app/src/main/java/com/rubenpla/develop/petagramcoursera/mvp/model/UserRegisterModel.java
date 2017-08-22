package com.rubenpla.develop.petagramcoursera.mvp.model;

public class UserRegisterModel {

    private String userId;
    private String userDevice;

    public UserRegisterModel() {};

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserDevice() {
        return userDevice;
    }

    public void setUserDevice(String userDevice) {
        this.userDevice = userDevice;
    }
}
