package com.rubenpla.develop.petagramcoursera.mvp.presenter;

import android.util.Log;

import com.rubenpla.develop.petagramcoursera.api.RetrofitController;
import com.rubenpla.develop.petagramcoursera.api.constants.ConstantesRestApi;
import com.rubenpla.develop.petagramcoursera.api.deserializer.SearchByUserDeserializer;
import com.rubenpla.develop.petagramcoursera.api.endpoints.RetrofitPetagramApi;
import com.rubenpla.develop.petagramcoursera.mvp.model.SearchByUserModel;
import com.rubenpla.develop.petagramcoursera.mvp.model.SearchByUserModelResponse;
import com.rubenpla.develop.petagramcoursera.mvp.presenter.basepresenter.BasePresenter;
import com.rubenpla.develop.petagramcoursera.mvp.view.IRegisterUserActivityView;
import com.rubenpla.develop.petagramcoursera.mvp.view.baseview.IBaseView;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterUserActivityPresenter extends BasePresenter implements IRegisterUserActivityPresenter{

    private final String TAG = RegisterUserActivityPresenter.class.getSimpleName();

    public RegisterUserActivityPresenter(IBaseView view) {
        super(view);
    }

    @Override
    public void setUserAccount(final String userName) throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {

        RetrofitController retrofitController = new RetrofitController(ConstantesRestApi.INSTA_ROOT_URL);
        RetrofitPetagramApi retrofitPetagramApi = retrofitController
                .setDeserializer(SearchByUserDeserializer.class.getName(),
                        SearchByUserModelResponse.class.getName());

        //Call Retrofit API to get if exists 'userId' value inside firebase's DB
        Call<SearchByUserModelResponse> searchByuserName = retrofitPetagramApi
                .searchUserByName(userName, ConstantesRestApi.ACCESS_TOKEN);

        searchByuserName.enqueue(new Callback<SearchByUserModelResponse>() {
            @Override
            public void onResponse(Call<SearchByUserModelResponse> call,
                                   Response<SearchByUserModelResponse> response) {

                if (response.body() == null) {
                    return; //NOT exists, return
                }

                ArrayList<SearchByUserModel> resultsList = response.body().getUserResultsList();
                SearchByUserModel userResult = null;
                for (SearchByUserModel user : resultsList) {
                    if (user.getUserName().contains(userName)) {
                        userResult = user;
                        Log.i(TAG, "User is assigned");
                    }
                }
                //If it exists we'll call PUT method, or POST if does not find any existig value
                //with this iserID

                //Once time Firebase DB were updated with new 'userId' value
                try {
                    ((IRegisterUserActivityView) getView()).switchToNewUserAccount(userResult);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<SearchByUserModelResponse> call, Throwable t) {
                Log.e(TAG, "getProfileInfo() --> Throwable :" + t.getLocalizedMessage());
            }
        });
    }
}
