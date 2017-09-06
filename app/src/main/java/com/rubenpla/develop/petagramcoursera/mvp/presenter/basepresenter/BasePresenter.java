package com.rubenpla.develop.petagramcoursera.mvp.presenter.basepresenter;

import com.rubenpla.develop.petagramcoursera.mvp.view.baseview.IBaseView;

public class BasePresenter implements IBasePresenter {

    protected IBaseView view;

    public BasePresenter(IBaseView view) {
        this.view = view;
    }

    @Override
    public IBaseView getView() {
        return view;
    }
}
