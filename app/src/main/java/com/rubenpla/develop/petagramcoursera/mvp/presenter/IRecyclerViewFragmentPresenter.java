package com.rubenpla.develop.petagramcoursera.mvp.presenter;

import android.view.View;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by alten on 10/8/17.
 */

public interface IRecyclerViewFragmentPresenter {
    void showPetRecentMediaList() throws NoSuchMethodException, ClassNotFoundException,
            IllegalAccessException, InstantiationException, InvocationTargetException;
    void showPetPhoto(View view, int position); //TODO
    void showUser(); //TODO
}
