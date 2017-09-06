package com.rubenpla.develop.petagramcoursera.mvp.presenter;

import com.rubenpla.develop.petagramcoursera.mvp.presenter.basepresenter.IBaseMediaPresenter;

import java.lang.reflect.InvocationTargetException;

public interface IRecyclerViewFragmentPresenter extends IBaseMediaPresenter {

    void getFollowedByUsersList() throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException;

    void showRecentMediaList() throws NoSuchMethodException, ClassNotFoundException,
            IllegalAccessException, InstantiationException, InvocationTargetException;
}
