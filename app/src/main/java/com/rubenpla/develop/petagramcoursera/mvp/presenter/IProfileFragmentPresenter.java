package com.rubenpla.develop.petagramcoursera.mvp.presenter;

import android.view.View;

import java.lang.reflect.InvocationTargetException;

public interface IProfileFragmentPresenter {
    void showProfileInfo() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;
}
