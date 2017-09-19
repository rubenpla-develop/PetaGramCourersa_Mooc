package com.rubenpla.develop.petagramcoursera.mvp.presenter.basepresenter;

import com.rubenpla.develop.petagramcoursera.mvp.view.baseview.IBaseView;

import java.lang.reflect.InvocationTargetException;

public interface IBasePresenter {
    IBaseView getView();

    void registerUser() throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException;
}
