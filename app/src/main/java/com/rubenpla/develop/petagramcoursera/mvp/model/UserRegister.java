package com.rubenpla.develop.petagramcoursera.mvp.model;

public class UserRegister {

    private String userId;
    private String deviceId;

    public UserRegister() {};

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
