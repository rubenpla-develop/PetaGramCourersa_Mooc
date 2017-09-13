package com.rubenpla.develop.petagramcoursera.mvp.model;

public class TokenResponse {

    private String id;
    private String deviceId;

    public TokenResponse() {};

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
