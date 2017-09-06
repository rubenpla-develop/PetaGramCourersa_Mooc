package com.rubenpla.develop.petagramcoursera.mvp.presenter;

import android.util.Log;

import com.rubenpla.develop.petagramcoursera.api.RetrofitController;
import com.rubenpla.develop.petagramcoursera.api.constants.ConstantesRestApi;
import com.rubenpla.develop.petagramcoursera.api.deserializer.MediaDataDeserializer;
import com.rubenpla.develop.petagramcoursera.api.deserializer.ProfileInfoDeserializer;
import com.rubenpla.develop.petagramcoursera.api.endpoints.RetrofitPetagramApi;
import com.rubenpla.develop.petagramcoursera.mvp.model.PetModel;
import com.rubenpla.develop.petagramcoursera.mvp.model.PetModelResponse;
import com.rubenpla.develop.petagramcoursera.mvp.model.ProfileInfoModelResponse;
import com.rubenpla.develop.petagramcoursera.mvp.presenter.basepresenter.BaseMediaPresenter;
import com.rubenpla.develop.petagramcoursera.mvp.view.IProfileFragmentView;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragmentPresenter extends BaseMediaPresenter implements IProfileFragmentPresenter {

    private RetrofitController controller;
    private RetrofitPetagramApi apiEndpoints;

    public ProfileFragmentPresenter(IProfileFragmentView view) {
        super(view);

        controller = new RetrofitController(ConstantesRestApi.INSTA_ROOT_URL);
    }

    @Override
    public void showProfileInfo() throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {

        apiEndpoints = controller.setDeserializer(ProfileInfoDeserializer.class.getName(),
                ProfileInfoModelResponse.class.getName());

        Call<ProfileInfoModelResponse> profileInfoCall = apiEndpoints.getProfileInfo();

        profileInfoCall.enqueue(new Callback<ProfileInfoModelResponse>() {
            @Override
            public void onResponse(Call<ProfileInfoModelResponse> call, Response<ProfileInfoModelResponse> response) {
                ProfileInfoModelResponse userInfoResponse = response.body();

                try {
                    ((IProfileFragmentView)view).showProfileFullName(userInfoResponse.getuser().getUserName());
                } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException
                        | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }

                ((IProfileFragmentView)view).showProfilePhoto(userInfoResponse.getuser().getUrlAvatar());
            }

            @Override
            public void onFailure(Call<ProfileInfoModelResponse> call, Throwable t) {
                Log.e(this.getClass().getName(), "ERROR LOADING USER INFO!!!!!");
            }
        });
    }

    @Override
    public void showPetRecentMediaList() throws NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        apiEndpoints = controller.setDeserializer(MediaDataDeserializer.class.getName(),
                PetModelResponse.class.getName());

        Call<PetModelResponse> recentMediaCall = apiEndpoints.getRecentPetMedia();

        recentMediaCall.enqueue(new Callback<PetModelResponse>() {

            @Override
            public void onResponse(Call<PetModelResponse> call, Response<PetModelResponse> response) {
                ArrayList<PetModel> petsList;
                PetModelResponse petResponse = response.body();
                petsList = petResponse.getPetModels();
                ((IProfileFragmentView) getView()).showGridList(petsList);
            }

            @Override
            public void onFailure(Call<PetModelResponse> call, Throwable t) {
            }
        });
    }
}
