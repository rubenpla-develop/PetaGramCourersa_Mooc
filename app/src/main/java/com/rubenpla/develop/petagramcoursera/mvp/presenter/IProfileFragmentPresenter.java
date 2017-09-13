package com.rubenpla.develop.petagramcoursera.mvp.presenter;

import com.rubenpla.develop.petagramcoursera.mvp.presenter.basepresenter.IBaseMediaPresenter;

import java.lang.reflect.InvocationTargetException;

public interface IProfileFragmentPresenter extends IBaseMediaPresenter {

    void showProfileInfo() throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException;

    void showPetRecentMediaList() throws NoSuchMethodException, ClassNotFoundException,
            IllegalAccessException, InstantiationException, InvocationTargetException;
}
