package com.gradtest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sm-pc on 2018-05-31.
 */

public class Net {
    private static Net ourInstance = new Net();

    public static Net getInstance() {
        return ourInstance;
    }

    private Net(){}

    private Retrofit retrofit = new Retrofit.Builder()
    .baseUrl("http://52.78.129.27:3000")
    .addConverterFactory(GsonConverterFactory.create()).build();

    NetworkService networkService;

    public NetworkService getNetworkService(){
        if(networkService == null){
            networkService = retrofit.create(NetworkService.class);
        }
        return networkService;
    }
}
