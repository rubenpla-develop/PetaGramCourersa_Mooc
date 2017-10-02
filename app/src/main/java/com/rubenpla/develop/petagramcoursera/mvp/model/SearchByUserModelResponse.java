package com.rubenpla.develop.petagramcoursera.mvp.model;

import java.util.ArrayList;

public class SearchByUserModelResponse {

    private ArrayList<SearchByUserModel> userResultsList;

    public SearchByUserModelResponse() {}

    public ArrayList<SearchByUserModel> getUserResultsList() {
        return userResultsList;
    }

    public void setUserResultsList(ArrayList<SearchByUserModel> userResultsList) {
        this.userResultsList = userResultsList;
    }
}
