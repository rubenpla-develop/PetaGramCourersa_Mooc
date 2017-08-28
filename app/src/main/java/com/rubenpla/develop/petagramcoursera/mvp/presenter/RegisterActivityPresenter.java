package com.rubenpla.develop.petagramcoursera.mvp.presenter;

import com.rubenpla.develop.petagramcoursera.mvp.view.IRegisterActivityView;

/**
 * Created by alten on 25/8/17.
 */

public class RegisterActivityPresenter implements IRegisterUserActivityPresenter {

    private IRegisterActivityView view;


    public RegisterActivityPresenter(IRegisterActivityView view) {
        this.view = view;
    }

    @Override
    public void setUserAccount() {
        //TODO sutff

        view.setUserAccount();
    }
}
