package com.rubenpla.develop.petagramcoursera.mvp.presenter.basepresenter;

import com.rubenpla.develop.petagramcoursera.mvp.view.baseview.IBaseView;
import com.rubenpla.develop.petagramcoursera.pojo.LikeMediaParams;

public class BaseMediaPresenter extends BasePresenter implements IBaseMediaPresenter {

    public BaseMediaPresenter(IBaseView view) {
        super(view);
    }

    @Override
    public void onClickLikeButton(LikeMediaParams params) {
        //TODO stuff
        view.showSnackBarSuccesMessage("Successful flow!!");
    }
}
