package com.rubenpla.develop.petagramcoursera.mvp.model;

public class PetModel {

    private String id;
    private String fullName;
    private String urlPhoto;
    private int likes = 0;

    public PetModel(String urlPhoto, String fullName, String email, int likes) {
        this.urlPhoto = urlPhoto;
        this.fullName = fullName;
        this.likes = likes;
    }

    public PetModel() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getfullName() {
        return fullName;
    }

    public void setfullName(String fullName) {
        this.fullName = fullName;
    }

    public String geturlPhoto() {
        return urlPhoto;
    }

    public void seturlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
