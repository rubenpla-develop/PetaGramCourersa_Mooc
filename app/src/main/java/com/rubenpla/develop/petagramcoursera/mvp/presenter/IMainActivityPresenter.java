package com.rubenpla.develop.petagramcoursera.mvp.presenter;

import java.lang.reflect.InvocationTargetException;

public interface IMainActivityPresenter {
    void getProfileInfo() throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException;

    void registerUser() throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException;
}
