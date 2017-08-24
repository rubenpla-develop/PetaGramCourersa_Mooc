package com.rubenpla.develop.petagramcoursera.api.endpoints;

import com.rubenpla.develop.petagramcoursera.api.constants.ConstantesRestApi;
import com.rubenpla.develop.petagramcoursera.mvp.model.FollowedByUserModelResponse;
import com.rubenpla.develop.petagramcoursera.mvp.model.PetModelResponse;
import com.rubenpla.develop.petagramcoursera.mvp.model.ProfileInfoModelResponse;
import com.rubenpla.develop.petagramcoursera.mvp.model.UserRegisterModelResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitPetagramApi {

    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER)
    Call<PetModelResponse> getRecentPetMedia();

    @GET(ConstantesRestApi.URL_GET_USER_SELF)
    Call<ProfileInfoModelResponse> getProfileInfo();

    @POST(ConstantesRestApi.KEY_API_POST_REGISTER_USER)
    @FormUrlEncoded
    Call<UserRegisterModelResponse> setUserRegister(@Field("idUser") String idUser,
                                                    @Field("idDevice") String idDevice);

    @GET(ConstantesRestApi.URL_GET_FOLLOWED_BY)
    Call<FollowedByUserModelResponse> getFollowedBy();

    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_BY_USER_ID)
    Call<PetModelResponse> getUserById(@Path ("user") String id);
}
