package com.rubenpla.develop.petagramcoursera.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rubenpla.develop.petagramcoursera.R;
import com.rubenpla.develop.petagramcoursera.adapter.PetGridAdapter;
import com.rubenpla.develop.petagramcoursera.mvp.model.PetModel;
import com.rubenpla.develop.petagramcoursera.mvp.presenter.RecyclerViewFragmentPresenter;
import com.rubenpla.develop.petagramcoursera.mvp.view.IRecyclerViewFragmentView;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewFragment extends Fragment implements IRecyclerViewFragmentView {

    @BindView(R.id.recyclerView) RecyclerView petRecyclerView;
    private GridLayoutManager layoutManager;
    private PetGridAdapter adapter;
    private RecyclerViewFragmentPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);

        ButterKnife.bind(this, view);

        //init components for view
        presenter = new RecyclerViewFragmentPresenter(this, getContext());
        layoutManager = new GridLayoutManager(getContext(), 2);
        petRecyclerView.setLayoutManager(layoutManager);

        //presenter's first call
        try {
            presenter.showPetRecentMediaList();
        } catch (NoSuchMethodException | ClassNotFoundException | IllegalAccessException |
                java.lang.InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return view;
    }

    @Override
    public void showGridList(ArrayList<PetModel> list) {
        adapter = new PetGridAdapter(getContext(), list);
        petRecyclerView.setAdapter(adapter);
    }

    @Override
    public void showPetPhoto(Context context,View view, int position) {
       //TODO
    }

}
