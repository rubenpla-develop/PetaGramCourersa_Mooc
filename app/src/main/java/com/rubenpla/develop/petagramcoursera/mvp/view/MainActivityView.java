package com.rubenpla.develop.petagramcoursera.mvp.view;

import android.content.Context;

import com.rubenpla.develop.petagramcoursera.mvp.model.PetModel;

import java.util.ArrayList;

public interface MainActivityView {
    void showGridList(ArrayList<PetModel> list);
    void showPetPhoto(Context context,int position);
    void showUserProfile();
}