package com.rubenpla.develop.petagramcoursera.mvp.presenter;

import android.view.View;

import java.lang.reflect.InvocationTargetException;

public interface IRecyclerViewFragmentPresenter {

    void getFollowedByUsersList() throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException;

    void showRecentMediaList() throws NoSuchMethodException, ClassNotFoundException,
            IllegalAccessException, InstantiationException, InvocationTargetException;

    void showPetPhoto(View view, int position); //TODO
    void showUser(); //TODO
}
