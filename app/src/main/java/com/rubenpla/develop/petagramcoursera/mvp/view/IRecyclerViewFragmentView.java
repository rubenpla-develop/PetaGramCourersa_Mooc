package com.rubenpla.develop.petagramcoursera.mvp.view;

import android.content.Context;
import android.view.View;

import com.rubenpla.develop.petagramcoursera.mvp.model.PetModel;

import java.util.ArrayList;

public interface IRecyclerViewFragmentView {
    void showGridList(ArrayList<PetModel> list);
    void showPetPhoto(Context context, View view, int position);
    void showUserProfile();
}
