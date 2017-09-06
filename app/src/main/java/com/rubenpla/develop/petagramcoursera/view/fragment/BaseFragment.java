package com.rubenpla.develop.petagramcoursera.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rubenpla.develop.petagramcoursera.MainActivity;
import com.rubenpla.develop.petagramcoursera.mvp.view.baseview.IBaseView;

public class BaseFragment extends Fragment implements IBaseView {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void showSnackBarSuccesMessage(String succesMessage) {
        ((MainActivity)getActivity()).showSnackBarSuccesMessage(succesMessage);
    }

    @Override
    public void showSnackBarErrorMessage(String errorMessage) {
        ((MainActivity)getActivity()).showSnackBarSuccesMessage(errorMessage);
    }
}
