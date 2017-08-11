package com.rubenpla.develop.petagramcoursera.mvp.view;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by alten on 11/8/17.
 */

public interface IProfileFragmentView {
    void showProfileFullName(String fullName) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;
    void showProfilePhoto(String profilePicUrl);
}
