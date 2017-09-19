package com.rubenpla.develop.petagramcoursera.mvp.view;

import com.rubenpla.develop.petagramcoursera.mvp.model.ProfileInfo;
import com.rubenpla.develop.petagramcoursera.mvp.view.baseview.IBaseView;

import java.lang.reflect.InvocationTargetException;

public interface MainActivityView extends IBaseView {
    void showUserProfile(ProfileInfo profileInfo);
    void registerUser() throws ClassNotFoundException, NoSuchMethodException,
            InstantiationException, IllegalAccessException, InvocationTargetException;
}