package com.rubenpla.develop.petagramcoursera.mvp.model;

import java.util.ArrayList;

public class FollowedByUserModelResponse {

    ArrayList<FollowedByUser> followersList;

    public void setFollowersList(ArrayList<FollowedByUser> followersList) {
        this.followersList = followersList;
    }

    public ArrayList<FollowedByUser> getFollowersList() {
        return followersList;
    }
}
