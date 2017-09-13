package com.rubenpla.develop.petagramcoursera.mvp.presenter.basepresenter;

import com.rubenpla.develop.petagramcoursera.mvp.model.UserRegister;
import com.rubenpla.develop.petagramcoursera.mvp.view.baseview.IBaseView;

public class BasePresenter implements IBasePresenter {

    private final String TAG = BasePresenter.class.getSimpleName();

    protected IBaseView view;
    protected UserRegister userRegister;

    public BasePresenter(IBaseView view) {
        this.view = view;
    }

    @Override
    public IBaseView getView() {
        return view;
    }
}
