package com.rubenpla.develop.petagramcoursera.mvp.model;

/**
 * Created by alten on 8/9/17.
 */

public class RegisterLikeModelResponse {
    private String id;
    private String mediaId;
    private String creatorId;
    private String deviceId;


    public RegisterLikeModelResponse() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
}
