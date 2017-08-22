package com.rubenpla.develop.petagramcoursera.mvp.presenter;

import android.content.Context;
import android.util.Log;

import com.rubenpla.develop.petagramcoursera.api.RetrofitController;
import com.rubenpla.develop.petagramcoursera.api.constants.ConstantesRestApi;
import com.rubenpla.develop.petagramcoursera.api.deserializer.ProfileInfoDeserializer;
import com.rubenpla.develop.petagramcoursera.api.endpoints.RetrofitPetagramApi;
import com.rubenpla.develop.petagramcoursera.mvp.model.UserProfileModelResponse;
import com.rubenpla.develop.petagramcoursera.mvp.model.UserProfile;
import com.rubenpla.develop.petagramcoursera.mvp.view.IProfileFragmentView;

import java.lang.reflect.InvocationTargetException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragmentPresenter implements IProfileFragmentPresenter {

    private Context context;
    private IProfileFragmentView view;
    private RetrofitController controller;
    private RetrofitPetagramApi apiEndpoints;
    private UserProfile userProfile;

    public ProfileFragmentPresenter(IProfileFragmentView view, Context context) {
        this.view = view;
        this.context = context;

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
}
