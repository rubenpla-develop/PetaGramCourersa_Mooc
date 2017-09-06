package com.rubenpla.develop.petagramcoursera.mvp.presenter;

import com.rubenpla.develop.petagramcoursera.mvp.presenter.basepresenter.BaseMediaPresenter;
import com.rubenpla.develop.petagramcoursera.mvp.presenter.basepresenter.IBaseMediaPresenter;
import com.rubenpla.develop.petagramcoursera.mvp.view.baseview.IBaseMediaView;

public class MediaDetailActivityPresenter extends BaseMediaPresenter implements IBaseMediaPresenter{

    public MediaDetailActivityPresenter(IBaseMediaView view) {
        super(view);
    }
}
