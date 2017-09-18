package com.rubenpla.develop.petagramcoursera.api.endpoints;

import com.rubenpla.develop.petagramcoursera.api.constants.ConstantesRestApi;
import com.rubenpla.develop.petagramcoursera.mvp.model.FollowedByUserModelResponse;
import com.rubenpla.develop.petagramcoursera.mvp.model.NullModelResponse;
import com.rubenpla.develop.petagramcoursera.mvp.model.PetModelResponse;
import com.rubenpla.develop.petagramcoursera.mvp.model.ProfileInfoModelResponse;
import com.rubenpla.develop.petagramcoursera.mvp.model.RegisterLikeModelResponse;
import com.rubenpla.develop.petagramcoursera.mvp.model.TokenResponse;
import com.rubenpla.develop.petagramcoursera.mvp.model.UserRegisterModelResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitPetagramApi {

    // <<<<<<<---------- INSTAGRAM Endpoints ---------->>>>>>>

    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER)
    Call<PetModelResponse> getRecentPetMedia();

    @GET(ConstantesRestApi.URL_GET_USER_SELF)
    Call<ProfileInfoModelResponse> getProfileInfo();

    @GET(ConstantesRestApi.URL_GET_FOLLOWED_BY)
    Call<FollowedByUserModelResponse> getFollowedBy();

    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_BY_USER_ID)
    Call<PetModelResponse> getUserById(@Path ("user") String id);

/*    @GET(ConstantesRestApi.URL_LIKE_PHOTO)
    Call<PetModelResponse> getLikes(@Path ("mediaId") String mediaId);*/

    @POST(ConstantesRestApi.URL_LIKE_PHOTO)
    Call<NullModelResponse> setLikeMedia(@Path("mediaId") String mediaId);

/*    @DELETE(ConstantesRestApi.URL_LIKE_PHOTO)
    Call<PetModelResponse> setUnlikeMedia(@Path ("mediaId") String mediaId);*/


    // <<<<<<<---------- HEROKU API ---> FIREBASE DB ---------->>>>>>>

    @POST(ConstantesRestApi.KEY_API_POST_REGISTER_USER)
    @FormUrlEncoded
    Call<UserRegisterModelResponse> setUserRegister(@Field("idUser") String idUser,
                                                    @Field("idDevice") String idDevice);

    @POST(ConstantesRestApi.KEY_API_POST_REGISTER_LIKE_MEDIA)
    @FormUrlEncoded
    Call<RegisterLikeModelResponse> registerLikeMedia(@Field ("userId") String userId,
                                                      @Field ("deviceId") String deviceId,
                                                      @Field ("mediaId") String mediaId);

    @GET(ConstantesRestApi.KEY_API_GET_USER_LIKE_MEDIA)
    Call<TokenResponse> sendNotificationToCreator(@Path("id") String id,
                                                  @Path("userId") String userId);

    @GET(ConstantesRestApi.KEY_API_GET_REGISTER_USER)
    Call<UserRegisterModelResponse> getRegisteredUser(@Path("id") String id);
}