package com.rubenpla.develop.petagramcoursera.mvp.presenter;

import com.rubenpla.develop.petagramcoursera.api.RetrofitController;
import com.rubenpla.develop.petagramcoursera.api.constants.ConstantesRestApi;
import com.rubenpla.develop.petagramcoursera.api.deserializer.FollowedByDeserializer;
import com.rubenpla.develop.petagramcoursera.api.deserializer.MediaDataDeserializer;
import com.rubenpla.develop.petagramcoursera.api.endpoints.RetrofitPetagramApi;
import com.rubenpla.develop.petagramcoursera.mvp.model.FollowedByUser;
import com.rubenpla.develop.petagramcoursera.mvp.model.FollowedByUserModelResponse;
import com.rubenpla.develop.petagramcoursera.mvp.model.PetModel;
import com.rubenpla.develop.petagramcoursera.mvp.model.PetModelResponse;
import com.rubenpla.develop.petagramcoursera.mvp.presenter.basepresenter.BaseMediaPresenter;
import com.rubenpla.develop.petagramcoursera.mvp.view.IRecyclerViewFragmentView;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerViewFragmentPresenter extends BaseMediaPresenter implements IRecyclerViewFragmentPresenter {

    private RetrofitController controller;
    private RetrofitPetagramApi apiEndpoints;
    private String userId;

    public RecyclerViewFragmentPresenter(IRecyclerViewFragmentView view) {
        super(view);

        controller = new RetrofitController(ConstantesRestApi.INSTA_ROOT_URL);
    }

    @Override
    public void getFollowedByUsersList() throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {

        apiEndpoints = controller.setDeserializer(FollowedByDeserializer.class.getName(),
                FollowedByUserModelResponse.class.getName());

        Call<FollowedByUserModelResponse> recentMediaCall = apiEndpoints.getFollowedBy();

        recentMediaCall.enqueue(new Callback<FollowedByUserModelResponse>() {

            @Override
            public void onResponse(Call<FollowedByUserModelResponse> call,
                                   Response<FollowedByUserModelResponse> response) {
                ArrayList<FollowedByUser> followersList;
                FollowedByUserModelResponse petResponse = response.body();
                followersList = petResponse.getFollowersList();

                if (followersList != null && followersList.size() > 0) {
                    for (FollowedByUser user : followersList) {
                        try {
                            userId = user.getUserId();
                            showRecentMediaList();
                        } catch (NoSuchMethodException|ClassNotFoundException|IllegalAccessException|
                                InstantiationException|InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<FollowedByUserModelResponse> call, Throwable t) {}
        });
    }

    @Override
    public void showRecentMediaList() throws NoSuchMethodException, ClassNotFoundException,
            IllegalAccessException, InstantiationException, InvocationTargetException {

        apiEndpoints = controller.setDeserializer(MediaDataDeserializer.class.getName(),
            PetModelResponse.class.getName());

        Call<PetModelResponse> recentMediaCall = apiEndpoints.getUserById(userId);

        recentMediaCall.enqueue(new Callback<PetModelResponse>() {

            @Override
            public void onResponse(Call<PetModelResponse> call, Response<PetModelResponse> response) {
                ArrayList<PetModel> petsList;
                PetModelResponse petResponse = response.body();
                petsList = petResponse.getPetModels();
                ((IRecyclerViewFragmentView) getView()).showGridList(petsList);
            }

            @Override
            public void onFailure(Call<PetModelResponse> call, Throwable t) {}
        });
    }
}
