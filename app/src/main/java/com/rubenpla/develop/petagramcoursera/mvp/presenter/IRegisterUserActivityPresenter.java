package com.rubenpla.develop.petagramcoursera.mvp.presenter;

import android.support.annotation.NonNull;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by alten on 25/8/17.
 */

public interface IRegisterUserActivityPresenter {
    void setUserAccount(@NonNull String userName) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;
}
