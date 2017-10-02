package com.rubenpla.develop.petagramcoursera.mvp.presenter.basepresenter;

import android.content.Context;
import android.util.Log;

import com.rubenpla.develop.petagramcoursera.R;
import com.rubenpla.develop.petagramcoursera.api.RetrofitController;
import com.rubenpla.develop.petagramcoursera.api.constants.ConstantesRestApi;
import com.rubenpla.develop.petagramcoursera.api.deserializer.RegisterUserDeserializer;
import com.rubenpla.develop.petagramcoursera.api.endpoints.RetrofitPetagramApi;
import com.rubenpla.develop.petagramcoursera.mvp.model.ProfileInfo;
import com.rubenpla.develop.petagramcoursera.mvp.model.UserRegister;
import com.rubenpla.develop.petagramcoursera.mvp.model.UserRegisterModelResponse;
import com.rubenpla.develop.petagramcoursera.mvp.view.baseview.IBaseView;

import java.lang.reflect.InvocationTargetException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BasePresenter implements IBasePresenter {

    private final String TAG = BasePresenter.class.getSimpleName();

    protected IBaseView view;
    protected UserRegister userRegister;

    public BasePresenter(IBaseView view) {
        this.view = view;
    }

    @Override
    public IBaseView getView() {
        return view;
    }

    @Override
    public void registerUser(ProfileInfo user) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        userRegister = new UserRegister(user);
        registerUser();
    }

    @Override
    public void registerUser() throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {

        Log.i(TAG, "registerUser() method called!");

        final RetrofitController retrofitController =
                new RetrofitController(ConstantesRestApi.API_ROOT_URL);

        RetrofitPetagramApi retrofitPetagramApi = retrofitController
                .setDeserializer(RegisterUserDeserializer.class.getName(),
                        UserRegisterModelResponse.class.getName());

        Call<UserRegisterModelResponse> registerUser = retrofitPetagramApi
                .setUserRegister(userRegister.getUserId(), userRegister.getDeviceId());

        registerUser.enqueue(new Callback<UserRegisterModelResponse>() {
            @Override
            public void onResponse(Call<UserRegisterModelResponse> call,
                                   Response<UserRegisterModelResponse> response) {
                getView().showSnackBarSuccesMessage(((Context) getView())
                        .getString(R.string.snackbar_register_user_success));
            }

            @Override
            public void onFailure(Call<UserRegisterModelResponse> call, Throwable t) {
                getView().showSnackBarErrorMessage(((Context) getView())
                        .getString(R.string.snackbar_register_user_error));
            }
        });
    }
}
