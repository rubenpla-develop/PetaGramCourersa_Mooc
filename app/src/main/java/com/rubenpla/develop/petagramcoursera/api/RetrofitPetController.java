package com.rubenpla.develop.petagramcoursera.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.rubenpla.develop.petagramcoursera.api.constants.ConstantesRestApi;
import com.rubenpla.develop.petagramcoursera.api.endpoints.RetrofitPetApi;
import com.rubenpla.develop.petagramcoursera.mvp.model.PetModel;
import com.rubenpla.develop.petagramcoursera.mvp.model.PetModelResponse;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitPetController {

    private RetrofitPetApi retrofitPetApi;
    private Call<? extends Object> request;

    /*public RetrofitPetApi initController() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(makeDefaultGson()))
                .build();

        retrofitPetApi = retrofit.create(RetrofitPetApi.class);

        return retrofitPetApi;
    }

    public Gson makeDefaultGson() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        return gson;
    }*/

    /*
     * Aqui se ha usado 'reflection' para obtener la clase de la cual se quiere obtener el
     * deserializador, de manera que proporcionandole el nombre de la clase en formato String
     * es capaz de obtener su constructor y de esta manera, tenemor un metodo universal para aplicar
     * cuantos serializadores tenga la aplicación.
     */
    public RetrofitPetApi setDeserializer(String clazz1) throws ClassNotFoundException,
            NoSuchMethodException, IllegalAccessException, InvocationTargetException,
            InstantiationException {

        Class clazz = Class.forName(clazz1);
        GsonBuilder builder = new GsonBuilder().registerTypeHierarchyAdapter(PetModelResponse.class,
                clazz.getConstructor().newInstance());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(builder.create()))
                .build();

        retrofitPetApi = retrofit.create(RetrofitPetApi.class);

        return retrofitPetApi;
    }

    /*public Call<? extends Object> getRecentMedia() {
        if (retrofitPetApi != null) {
            request = retrofitPetApi.getRecentPetMedia();
        }

        return request;
    }

    public Call<? extends Object> getUserProfileInfo() {
        if (retrofitPetApi != null) {
            request = retrofitPetApi.getSelfUserInfo();
        }

        return request;
    }*/
}
