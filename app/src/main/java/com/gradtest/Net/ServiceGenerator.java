package com.gradtest.Net;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gradtest.BuildConfig;
import com.gradtest.Net.RemoteService;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by sm-pc on 2018-05-28.
 */

public class ServiceGenerator {
    public static <S> S createService(Class<S> serviceClass){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        if(BuildConfig.DEBUG){
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        }else{
            logging.setLevel(HttpLoggingInterceptor.Level.NONE);
        }

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(RemoteService.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).client(httpClient.build()).build();

        return retrofit.create(serviceClass);
    }
}
