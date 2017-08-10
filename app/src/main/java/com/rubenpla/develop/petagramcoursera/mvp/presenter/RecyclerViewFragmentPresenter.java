package com.rubenpla.develop.petagramcoursera.mvp.presenter;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.rubenpla.develop.petagramcoursera.api.RetrofitPetController;
import com.rubenpla.develop.petagramcoursera.api.deserializer.RecentMediaDeserializer;
import com.rubenpla.develop.petagramcoursera.api.endpoints.RetrofitPetApi;
import com.rubenpla.develop.petagramcoursera.mvp.model.PetModel;
import com.rubenpla.develop.petagramcoursera.mvp.model.PetModelResponse;
import com.rubenpla.develop.petagramcoursera.mvp.view.IRecyclerViewFragmentView;
import com.rubenpla.develop.petagramcoursera.mvp.view.MainActivityView;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerViewFragmentPresenter implements IRecyclerViewFragmentPresenter {

    private RetrofitPetController controller;
    private RetrofitPetApi apiEndpoints;
    private Context context;
    private IRecyclerViewFragmentView view;
    private ArrayList<PetModel> petsList;

    public RecyclerViewFragmentPresenter(IRecyclerViewFragmentView view, Context context) {
        this.view = view;
        this.context = context;

        controller = new RetrofitPetController();
    }


    @Override
    public void showPetRecentMediaList() throws NoSuchMethodException, ClassNotFoundException,
            IllegalAccessException, InstantiationException, InvocationTargetException {

        apiEndpoints = controller.setDeserializer(RecentMediaDeserializer.class.getName());
        Call<PetModelResponse> recentMediaCall = apiEndpoints.getRecentPetMedia();

        recentMediaCall.enqueue(new Callback<PetModelResponse>() {

            @Override
            public void onResponse(Call<PetModelResponse> call, Response<PetModelResponse> response) {
                PetModelResponse petResponse = response.body();
                petsList = petResponse.getPetModels();
                view.showGridList(petsList); //TODO pass deserialized object param to adapter
            }

            @Override
            public void onFailure(Call<PetModelResponse> call, Throwable t) {
                Toast.makeText(context, "Error al obtener los medios, vuelve a intentarlo" +
                        "por favor", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void showPetPhoto(View view, int position) throws NullPointerException {
        //TODO
    }

    @Override
    public void showUser() throws NullPointerException {
        //TODO
        view.showUserProfile();
    }
}
