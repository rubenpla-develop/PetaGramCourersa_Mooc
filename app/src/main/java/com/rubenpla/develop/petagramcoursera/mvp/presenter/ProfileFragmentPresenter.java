package com.rubenpla.develop.petagramcoursera.mvp.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.rubenpla.develop.petagramcoursera.api.RetrofitController;
import com.rubenpla.develop.petagramcoursera.api.constants.ConstantesRestApi;
import com.rubenpla.develop.petagramcoursera.api.deserializer.ProfileInfoDeserializer;
import com.rubenpla.develop.petagramcoursera.api.deserializer.RecentMediaDeserializer;
import com.rubenpla.develop.petagramcoursera.api.endpoints.RetrofitPetagramApi;
import com.rubenpla.develop.petagramcoursera.mvp.model.PetModel;
import com.rubenpla.develop.petagramcoursera.mvp.model.PetModelResponse;
import com.rubenpla.develop.petagramcoursera.mvp.model.UserProfileModelResponse;
import com.rubenpla.develop.petagramcoursera.mvp.model.UserProfile;
import com.rubenpla.develop.petagramcoursera.mvp.view.IProfileFragmentView;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragmentPresenter implements IProfileFragmentPresenter {

    private IProfileFragmentView view;
    private RetrofitController controller;
    private RetrofitPetagramApi apiEndpoints;
    private UserProfile userProfile;

    public ProfileFragmentPresenter(IProfileFragmentView view) {
        this.view = view;

        controller = new RetrofitController(ConstantesRestApi.INSTA_ROOT_URL);
    }

    @Override
    public void showProfileInfo() throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {

        apiEndpoints = controller.setDeserializer(ProfileInfoDeserializer.class.getName(),
                UserProfileModelResponse.class.getName());

        Call<UserProfileModelResponse> profileInfoCall = apiEndpoints.getProfileInfo();

        profileInfoCall.enqueue(new Callback<UserProfileModelResponse>() {
            @Override
            public void onResponse(Call<UserProfileModelResponse> call, Response<UserProfileModelResponse> response) {
                UserProfileModelResponse userInfoResponse = response.body();

                try {
                    view.showProfileFullName(userInfoResponse.getuser().getUserName());
                } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException
                        | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }

                view.showProfilePhoto(userInfoResponse.getuser().getUrlAvatar());
            }

            @Override
            public void onFailure(Call<UserProfileModelResponse> call, Throwable t) {
                Log.e(this.getClass().getName(), "ERROR LOADING USER INFO!!!!!");
            }
        });
    }

    @Override
    public void showPetRecentMediaList() throws NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        apiEndpoints = controller.setDeserializer(RecentMediaDeserializer.class.getName(),
                PetModelResponse.class.getName());

        Call<PetModelResponse> recentMediaCall = apiEndpoints.getRecentPetMedia();

        recentMediaCall.enqueue(new Callback<PetModelResponse>() {

            @Override
            public void onResponse(Call<PetModelResponse> call, Response<PetModelResponse> response) {
                ArrayList<PetModel> petsList;
                PetModelResponse petResponse = response.body();
                petsList = petResponse.getPetModels();
                view.showGridList(petsList);
            }

            @Override
            public void onFailure(Call<PetModelResponse> call, Throwable t) {}
        });
    }
}
