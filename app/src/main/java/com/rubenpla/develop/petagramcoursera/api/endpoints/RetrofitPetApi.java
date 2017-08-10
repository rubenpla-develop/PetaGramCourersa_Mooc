package com.rubenpla.develop.petagramcoursera.api.endpoints;

import com.rubenpla.develop.petagramcoursera.api.constants.ConstantesRestApi;
import com.rubenpla.develop.petagramcoursera.mvp.model.PetModel;
import com.rubenpla.develop.petagramcoursera.mvp.model.PetModelResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitPetApi {

    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER)
    Call<PetModelResponse> getRecentPetMedia();


    @GET(ConstantesRestApi.URL_GET_SELF_USER_INFO)
    Call<Object> getSelfUserInfo();
}
