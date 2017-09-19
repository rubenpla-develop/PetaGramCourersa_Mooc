package com.rubenpla.develop.petagramcoursera.mvp.presenter;

import android.util.Log;

import com.rubenpla.develop.petagramcoursera.api.RetrofitController;
import com.rubenpla.develop.petagramcoursera.api.constants.ConstantesRestApi;
import com.rubenpla.develop.petagramcoursera.api.deserializer.ProfileInfoDeserializer;
import com.rubenpla.develop.petagramcoursera.api.endpoints.RetrofitPetagramApi;
import com.rubenpla.develop.petagramcoursera.mvp.model.ProfileInfo;
import com.rubenpla.develop.petagramcoursera.mvp.model.ProfileInfoModelResponse;
import com.rubenpla.develop.petagramcoursera.mvp.presenter.basepresenter.BasePresenter;
import com.rubenpla.develop.petagramcoursera.mvp.view.MainActivityView;
import com.rubenpla.develop.petagramcoursera.mvp.view.baseview.IBaseView;

import java.lang.reflect.InvocationTargetException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityPresenter extends BasePresenter implements IMainActivityPresenter {

    private final String TAG = MainActivityPresenter.class.getSimpleName();

    public MainActivityPresenter(IBaseView view) {
        super(view);
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

                ProfileInfo profileInfo = new ProfileInfo();

                profileInfo.setUserId(response.body().getuser().getUserId());
                profileInfo.setUserName(response.body().getuser().getUserName());
                profileInfo.setUrlAvatar(response.body().getuser().getUrlAvatar());

                ((MainActivityView) getView()).showUserProfile(profileInfo);
                //String deviceId = FirebaseInstanceId.getInstance().getToken();

                /*userRegister = new UserRegister();
                userRegister.setUserId(userId);
                userRegister.setDeviceId(deviceId);

                try {
                    registerUser();
                } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException
                        | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }*/
            }

            @Override
            public void onFailure(Call<ProfileInfoModelResponse> call, Throwable t) {
                Log.e(TAG, "getProfileInfo() --> Throwable :" + t.getLocalizedMessage());
            }
        });
    }


}
