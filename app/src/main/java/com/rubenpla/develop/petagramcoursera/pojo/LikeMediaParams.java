package com.rubenpla.develop.petagramcoursera.pojo;

import com.google.firebase.iid.FirebaseInstanceId;

public class LikeMediaParams {
    private String userId;
    private String deviceId;
    private String mediaId;

    public LikeMediaParams(String userId, String mediaId) {
        this.userId = userId;
        this.mediaId = mediaId;
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

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
}
