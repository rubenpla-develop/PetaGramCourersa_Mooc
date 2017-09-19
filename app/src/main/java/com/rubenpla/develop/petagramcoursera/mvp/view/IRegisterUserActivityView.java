package com.rubenpla.develop.petagramcoursera.mvp.view;

import com.rubenpla.develop.petagramcoursera.mvp.view.baseview.IBaseView;

import java.lang.reflect.InvocationTargetException;

public interface IRegisterUserActivityView extends IBaseView{
    void switchToNewUserAccount(String userId);

    void registerUser() throws ClassNotFoundException, NoSuchMethodException,
            InstantiationException, IllegalAccessException, InvocationTargetException;

    void finishAndReturn();

}
