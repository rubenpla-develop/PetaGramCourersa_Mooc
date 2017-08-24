package com.rubenpla.develop.petagramcoursera.mvp.view;

import com.rubenpla.develop.petagramcoursera.mvp.model.PetModel;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public interface IProfileFragmentView {
    void showProfileFullName(String fullName) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;
    void showProfilePhoto(String profilePicUrl);
    void showGridList(ArrayList<PetModel> list);
}
