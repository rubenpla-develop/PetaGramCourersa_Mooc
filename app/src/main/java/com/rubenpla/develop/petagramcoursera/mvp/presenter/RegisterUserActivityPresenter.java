package com.rubenpla.develop.petagramcoursera.mvp.presenter;

import com.rubenpla.develop.petagramcoursera.mvp.presenter.basepresenter.BasePresenter;
import com.rubenpla.develop.petagramcoursera.mvp.view.IRegisterUserActivityView;
import com.rubenpla.develop.petagramcoursera.mvp.view.baseview.IBaseView;

public class RegisterUserActivityPresenter extends BasePresenter implements IRegisterUserActivityPresenter{

    public RegisterUserActivityPresenter(IBaseView view) {
        super(view);
    }

    @Override
    public void setUserAccount(String userId) {
        //Call Retrofit API to get if exists 'userId' value inside firebase's DB


        //If it exists we'll call PUT method, or POST if does not find any existig value
        //with this iserID

        //Once time Firebase DB were updated with new 'userId' value
        ((IRegisterUserActivityView) getView()).switchToNewUserAccount(null);
    }
}
