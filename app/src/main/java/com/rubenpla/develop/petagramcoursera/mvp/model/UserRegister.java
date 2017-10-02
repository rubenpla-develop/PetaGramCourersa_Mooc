package com.rubenpla.develop.petagramcoursera.mvp.model;

import com.google.firebase.iid.FirebaseInstanceId;

public class UserRegister {

    private String userId;
    private String deviceId;

    public UserRegister() {};

    public UserRegister(ProfileInfo profile) {
        this.userId = profile.getUserId();
        this.deviceId = FirebaseInstanceId.getInstance().getToken();
    }

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
