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
import android.widget.ImageView;
import android.widget.TextView;

import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
import com.rubenpla.develop.petagramcoursera.R;
import com.rubenpla.develop.petagramcoursera.adapter.PetGridAdapter;
import com.rubenpla.develop.petagramcoursera.mvp.model.PetModel;
import com.rubenpla.develop.petagramcoursera.mvp.presenter.ProfileFragmentPresenter;
import com.rubenpla.develop.petagramcoursera.mvp.presenter.RecyclerViewFragmentPresenter;
import com.rubenpla.develop.petagramcoursera.mvp.view.IProfileFragmentView;
import com.rubenpla.develop.petagramcoursera.mvp.view.IRecyclerViewFragmentView;
import com.squareup.picasso.Picasso;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileFragment extends Fragment implements IRecyclerViewFragmentView,
        IProfileFragmentView {

    @BindView(R.id.recyclerView) RecyclerView petRecyclerView;
    @BindView(R.id.rv_header) RecyclerViewHeader rvHeader;
    @BindView(R.id.user_full_name) TextView tvUserName;
    @BindView(R.id.user_image) ImageView ivProfilePicture;
    private GridLayoutManager layoutManager;
    private PetGridAdapter adapter;
    private RecyclerViewFragmentPresenter recyclerViewFragmentPresenter;
    private ProfileFragmentPresenter profileFragmentPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);

        ButterKnife.bind(this, view);

        //init Presenters
        recyclerViewFragmentPresenter = new RecyclerViewFragmentPresenter(this, getContext());
        profileFragmentPresenter = new ProfileFragmentPresenter(this, getContext());

        //init components for view
        layoutManager = new GridLayoutManager(getContext(), 2);
        petRecyclerView.setLayoutManager(layoutManager);
        rvHeader.attachTo(petRecyclerView);

        //recyclerViewFragmentPresenter's first call
        try {

            recyclerViewFragmentPresenter.showPetRecentMediaList();
            profileFragmentPresenter.showProfileInfo();
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
    public void showPetPhoto(Context context, View view, int position) {

    }

    @Override
    public void showProfileFullName(String fullName) {
        tvUserName.setText(fullName);
    }

    @Override
    public void showProfilePhoto(String profilePicUrl) {
        Picasso.with(getContext())
                .load(profilePicUrl)
                .placeholder(R.drawable.ic_contacts)
                .error(R.drawable.shock_rave_icon)
                .into(ivProfilePicture);
    }
}
