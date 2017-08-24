package com.rubenpla.develop.petagramcoursera.mvp.view;

import android.content.Context;

import com.rubenpla.develop.petagramcoursera.mvp.model.PetModel;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public interface MainActivityView {
    void showSnackBarSuccesMessage(String succesMessage);
    void showSnackBarErrorMessage(String errorMessage);
    void showUserProfile();
    void registerUser() throws ClassNotFoundException, NoSuchMethodException,
            InstantiationException, IllegalAccessException, InvocationTargetException;
}