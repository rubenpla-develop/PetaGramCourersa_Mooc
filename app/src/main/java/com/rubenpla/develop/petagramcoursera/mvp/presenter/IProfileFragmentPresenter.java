package com.rubenpla.develop.petagramcoursera.mvp.presenter;

import com.rubenpla.develop.petagramcoursera.mvp.presenter.basepresenter.IBasePresenter;

import java.lang.reflect.InvocationTargetException;

public interface IProfileFragmentPresenter extends IBasePresenter {

    void showProfileInfo() throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException;

    void showPetRecentMediaList() throws NoSuchMethodException, ClassNotFoundException,
            IllegalAccessException, InstantiationException, InvocationTargetException;
}
