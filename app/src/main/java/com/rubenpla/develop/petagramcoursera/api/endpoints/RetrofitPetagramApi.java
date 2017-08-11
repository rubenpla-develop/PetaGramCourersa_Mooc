package com.rubenpla.develop.petagramcoursera.api.endpoints;

import com.rubenpla.develop.petagramcoursera.api.constants.ConstantesRestApi;
import com.rubenpla.develop.petagramcoursera.mvp.model.PetModelResponse;
import com.rubenpla.develop.petagramcoursera.mvp.model.UserProfileModelResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitPetagramApi {

    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER)
    Call<PetModelResponse> getRecentPetMedia();

    @GET(ConstantesRestApi.URL_GET_USER_SELF)
    Call<UserProfileModelResponse> getProfileInfo();
}
