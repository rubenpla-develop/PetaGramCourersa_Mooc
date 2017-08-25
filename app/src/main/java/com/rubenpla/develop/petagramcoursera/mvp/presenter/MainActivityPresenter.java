package com.rubenpla.develop.petagramcoursera.mvp.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.rubenpla.develop.petagramcoursera.R;
import com.rubenpla.develop.petagramcoursera.api.RetrofitController;
import com.rubenpla.develop.petagramcoursera.api.constants.ConstantesRestApi;
import com.rubenpla.develop.petagramcoursera.api.deserializer.ProfileInfoDeserializer;
import com.rubenpla.develop.petagramcoursera.api.endpoints.RetrofitPetagramApi;
import com.rubenpla.develop.petagramcoursera.mvp.model.ProfileInfoModelResponse;
import com.rubenpla.develop.petagramcoursera.mvp.model.UserRegister;
import com.rubenpla.develop.petagramcoursera.mvp.model.UserRegisterModelResponse;
import com.rubenpla.develop.petagramcoursera.mvp.view.MainActivityView;

import java.lang.reflect.InvocationTargetException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityPresenter implements IMainActivityPresenter {

    private final String TAG = MainActivityPresenter.class.getSimpleName();

    private MainActivityView view;
    private UserRegister userRegister;

    public MainActivityPresenter(@NonNull MainActivityView view) {
        this.view = view;
    }

    @Override
    public void getProfileInfo() throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {

        final RetrofitController retrofitController =
                new RetrofitController(ConstantesRestApi.INSTA_ROOT_URL);

        RetrofitPetagramApi retrofitPetagramApi = retrofitController
                .setDeserializer(ProfileInfoDeserializer.class.getName(),
                        ProfileInfoModelResponse.class.getName());

        Call<ProfileInfoModelResponse> ProfileInfoCall = retrofitPetagramApi.getProfileInfo();

        ProfileInfoCall.enqueue(new Callback<ProfileInfoModelResponse>() {
            @Override
            public void onResponse(Call<ProfileInfoModelResponse> call,
                                   Response<ProfileInfoModelResponse> response) {

                String userId = response.body().getuser().getUserId();
                String deviceId = FirebaseInstanceId.getInstance().getToken();

                userRegister = new UserRegister();
                userRegister.setUserId(userId);
                userRegister.setDeviceId(deviceId);

                try {
                    registerUser();
                } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException
                        | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ProfileInfoModelResponse> call, Throwable t) {
                Log.e(TAG, "getProfileInfo() --> Throwable :" + t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void registerUser() throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {

        Log.i(TAG, "registerUser() method called!");

        final RetrofitController retrofitController =
                new RetrofitController(ConstantesRestApi.API_ROOT_URL);

        RetrofitPetagramApi retrofitPetagramApi = retrofitController
                .setDeserializer(ProfileInfoDeserializer.class.getName(),
                        UserRegisterModelResponse.class.getName());

        Call<UserRegisterModelResponse> registerUser = retrofitPetagramApi.
                setUserRegister(userRegister.getUserId(), userRegister.getDeviceId());

        registerUser.enqueue(new Callback<UserRegisterModelResponse>() {
            @Override
            public void onResponse(Call<UserRegisterModelResponse> call, Response<UserRegisterModelResponse> response) {
                    view.showSnackBarSuccesMessage(((Context) view).
                            getString(R.string.snackbar_register_user_success));
            }

            @Override
            public void onFailure(Call<UserRegisterModelResponse> call, Throwable t) {
                view.showSnackBarErrorMessage(((Context) view)
                        .getString(R.string.snackbar_register_user_error));
            }
        });

    }
}