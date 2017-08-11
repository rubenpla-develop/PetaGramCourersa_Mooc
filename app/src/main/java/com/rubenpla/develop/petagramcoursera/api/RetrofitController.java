package com.rubenpla.develop.petagramcoursera.api;

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

    /*public RetrofitPetagramApi initController() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(makeDefaultGson()))
                .build();

        retrofitPetagramApi = retrofit.create(RetrofitPetagramApi.class);

        return retrofitPetagramApi;
    }

    public Gson makeDefaultGson() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        return gson;
    }*/

    /*
     * Aqui se ha usado 'reflection' para obtener la clase de la cual se quiere obtener el
     * deserializador, de manera que proporcionandole el nombre de la clase en formato String, y el
     * de la response de la llamada al webservice,
     * es capaz de obtener su constructor y de esta manera y la clase respectivamente,
     * tenemos un metodo universal para aplicar
     * cuantos serializadores tenga la aplicación.
     */
    public RetrofitPetagramApi setDeserializer(String serializerClass, String responseClass) throws ClassNotFoundException,
            NoSuchMethodException, IllegalAccessException, InvocationTargetException,
            InstantiationException {

        Class serializer = Class.forName(serializerClass);
        Class response = Class.forName(responseClass);
        GsonBuilder builder = new GsonBuilder().registerTypeHierarchyAdapter( response.getConstructor().getDeclaringClass(),
                serializer.getConstructor().newInstance());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(builder.create()))
                .build();

        retrofitPetagramApi = retrofit.create(RetrofitPetagramApi.class);

        return retrofitPetagramApi;
    }

    /*public Call<? extends Object> getRecentMedia() {
        if (retrofitPetagramApi != null) {
            request = retrofitPetagramApi.getRecentPetMedia();
        }

        return request;
    }

    public Call<? extends Object> getUserProfileInfo() {
        if (retrofitPetagramApi != null) {
            request = retrofitPetagramApi.getSelfUserInfo();
        }

        return request;
    }*/
}
