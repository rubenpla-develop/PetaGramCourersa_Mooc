package com.rubenpla.develop.petagramcoursera.api;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rubenpla.develop.petagramcoursera.api.constants.ConstantesRestApi;
import com.rubenpla.develop.petagramcoursera.api.endpoints.RetrofitPetagramApi;

import java.lang.reflect.InvocationTargetException;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitController {

    private RetrofitPetagramApi retrofitPetagramApi;
    private Call<? extends Object> request;
    private String rootUrl;

    public RetrofitController (@NonNull String rootUrl) {
        this.rootUrl = rootUrl;
    }

    /*public Gson makeDefaultGson() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        return gson;
    }*/

    /*
     * Aqui se ha usado 'reflection' para obtener la clase de la cual se quiere obtener el
     * deserializador, de manera que proporcionandole el nombre de la clase en formato String, y el
     * de la response de la llamada 4al webservice,
     * es capaz de obtener su constructor y de esta manera y la clase respectivamente,
     * tenemos un metodo universal para aplicar
     * cuantos serializadores tenga la aplicación.
     */
    public RetrofitPetagramApi setDeserializer(@NonNull String serializerClass,@NonNull String responseClass)
            throws ClassNotFoundException,
            NoSuchMethodException, IllegalAccessException, InvocationTargetException,
            InstantiationException {

        Class serializer = Class.forName(serializerClass);
        Class response = Class.forName(responseClass);
        GsonBuilder builder = new GsonBuilder()
                .registerTypeHierarchyAdapter(response.getConstructor().getDeclaringClass(),
                serializer.getConstructor().newInstance());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(rootUrl)
                .addConverterFactory(GsonConverterFactory.create(builder.create()))
                .build();

        retrofitPetagramApi = retrofit.create(RetrofitPetagramApi.class);

        return retrofitPetagramApi;
    }

    public void setRootUrl(@NonNull String rootUrl) {
        this.rootUrl = rootUrl;
    }
}
