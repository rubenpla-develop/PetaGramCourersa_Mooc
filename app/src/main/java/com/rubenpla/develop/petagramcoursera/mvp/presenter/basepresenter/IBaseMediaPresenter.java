package com.rubenpla.develop.petagramcoursera.mvp.presenter.basepresenter;

import com.rubenpla.develop.petagramcoursera.pojo.LikeMediaParams;

public interface IBaseMediaPresenter extends IBasePresenter {
    void onClickLikeButton(LikeMediaParams params);
}
