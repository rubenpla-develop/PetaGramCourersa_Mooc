package com.rubenpla.develop.petagramcoursera.api.endpoints;

import com.rubenpla.develop.petagramcoursera.api.constants.ConstantesRestApi;
import com.rubenpla.develop.petagramcoursera.mvp.model.PetModelResponse;
import com.rubenpla.develop.petagramcoursera.mvp.model.UserProfileModelResponse;
import com.rubenpla.develop.petagramcoursera.mvp.model.UserRegisterModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitPetagramApi {

    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER)
    Call<PetModelResponse> getRecentPetMedia();

    @GET(ConstantesRestApi.URL_GET_USER_SELF)
    Call<UserProfileModelResponse> getProfileInfo();

    @POST(ConstantesRestApi.KEY_API_POST_REGISTER_USER)
    Call<UserRegisterModel> setUserRegister();
}
