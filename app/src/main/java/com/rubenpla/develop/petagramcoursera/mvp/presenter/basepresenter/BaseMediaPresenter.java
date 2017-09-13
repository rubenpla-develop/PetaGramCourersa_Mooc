package com.rubenpla.develop.petagramcoursera.mvp.presenter.basepresenter;

import android.util.Log;

import com.rubenpla.develop.petagramcoursera.api.RetrofitController;
import com.rubenpla.develop.petagramcoursera.api.constants.ConstantesRestApi;
import com.rubenpla.develop.petagramcoursera.api.endpoints.RetrofitPetagramApi;
import com.rubenpla.develop.petagramcoursera.mvp.model.NullModelResponse;
import com.rubenpla.develop.petagramcoursera.mvp.model.RegisterLikeModelResponse;
import com.rubenpla.develop.petagramcoursera.mvp.model.TokenResponse;
import com.rubenpla.develop.petagramcoursera.mvp.view.baseview.IBaseView;
import com.rubenpla.develop.petagramcoursera.pojo.LikeMediaParams;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseMediaPresenter extends BasePresenter implements IBaseMediaPresenter {

    private final int RESPONSE_CODE_OK = 200;
    private final int RESPONSE_CODE_UNAUTHORIZED = 404;

    public BaseMediaPresenter(IBaseView view) {
        super(view);
    }

    @Override
    public void onClickLikeButton(final LikeMediaParams params) {
        //TODO stuff or override
        final RetrofitController retrofitController =
                new RetrofitController(ConstantesRestApi.INSTA_ROOT_URL);

        RetrofitPetagramApi retrofitPetagramApi = retrofitController.buildDefaultController();

        /*
         * Instagram Api call ----> like ENdpoint method POST
         */
        Call<NullModelResponse> likeImageCall = retrofitPetagramApi
                .setLikeMedia(params.getMediaId());

        likeImageCall.enqueue(new Callback<NullModelResponse>() {
            @Override
            public void onResponse(Call<NullModelResponse> call,
                                   Response<NullModelResponse> response) {
                getView().showSnackBarSuccesMessage("Instagram's Response code" + String.valueOf(response.code()));

                if (response.code() == RESPONSE_CODE_OK) {
                    registerLikeInfo(params);
                }
            }

            @Override
            public void onFailure(Call<NullModelResponse> call, Throwable t) {
                getView().showSnackBarErrorMessage("Firebase Error Throwable : " + t.getLocalizedMessage());
            }
        });

    }

    @Override
    public void registerLikeInfo(final LikeMediaParams params) {
        final RetrofitController retrofitController =
                new RetrofitController(ConstantesRestApi.API_ROOT_URL);

        RetrofitPetagramApi retrofitPetagramApi = retrofitController.buildDefaultController();

        /*
         * Heroku API call ----> registrar-like endpoint, POST method
         */
        Call<RegisterLikeModelResponse> registerLikeInfoCall = retrofitPetagramApi
                .registerLikeMedia(params.getUserId(), params.getDeviceId(), params.getMediaId());

        registerLikeInfoCall.enqueue(new Callback<RegisterLikeModelResponse>() {
            @Override
            public void onResponse(Call<RegisterLikeModelResponse> call,
                                   Response<RegisterLikeModelResponse> response) {

                getView().showSnackBarSuccesMessage("Firebase's Response code :" + response.code());

                if (response.code() == RESPONSE_CODE_OK) {
                    sendNotification(response.body());
                }
            }

            @Override
            public void onFailure(Call<RegisterLikeModelResponse> call, Throwable t) {
                getView().showSnackBarErrorMessage("Firebase Error Throwable : " + t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void sendNotification(RegisterLikeModelResponse response) {
        RetrofitController retrofitController = new RetrofitController(ConstantesRestApi.API_ROOT_URL);
        RetrofitPetagramApi endPoints = retrofitController.buildDefaultController();
        Call<TokenResponse> userResponseCall = endPoints.sendNotificationToCreator(response.getId(),
                response.getCreatorId());

        userResponseCall.enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
               TokenResponse finalResponse = response.body();
                getView().showSnackBarSuccesMessage("Notification sent to user");
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                Log.e("TAG", "Error!!!: " + t.getMessage());
                getView().showSnackBarErrorMessage("ERROR : " + t.getLocalizedMessage());
            }
        });
    }
}
