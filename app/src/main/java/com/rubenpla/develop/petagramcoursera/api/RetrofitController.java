package com.rubenpla.develop.petagramcoursera.api;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rubenpla.develop.petagramcoursera.api.endpoints.RetrofitPetagramApi;
import com.rubenpla.develop.petagramcoursera.application.PetagramCourseraApplication;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitController {

    private RetrofitPetagramApi retrofitPetagramApi;
    private String rootUrl;
    private File cacheDir;
    private Cache cache;
    private OkHttpClient httpClient;

    public RetrofitController (@NonNull String rootUrl) {
        this.rootUrl = rootUrl;

        int cacheSize = 10 * 1024 * 1024; //10mb
        cacheDir = PetagramCourseraApplication.getInstance().getCacheDir();
        cache = new Cache(cacheDir, cacheSize);

        httpClient = new OkHttpClient.Builder()
                .cache(cache)
                .build();
    }

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
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create(builder.create()))
                .build();

        retrofitPetagramApi = retrofit.create(RetrofitPetagramApi.class);

        return retrofitPetagramApi;
    }

    public RetrofitPetagramApi buildDefaultController() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(rootUrl)
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
    }

    public void setRootUrl(@NonNull String rootUrl) {
        this.rootUrl = rootUrl;
    }

    private static class ResponseCacheInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {

            Request request = chain.request();
            if (Boolean.valueOf(request.header("ApplyResponseCache"))) {
                Log.i(RetrofitController.class.getSimpleName(), "Response cache applied");
                Response originalResponse = chain.proceed(chain.request());
                return originalResponse.newBuilder()
                        .removeHeader("ApplyResponseCache")
                        .header("Cache-Control", "public, max-age=" + 60)
                        .build();
            } else {
                Log.i(RetrofitController.class.getSimpleName(), "Response cache not applied");
                return chain.proceed(chain.request());
            }
        }
    }
}