package com.gradtest.Net;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sm-pc on 2018-05-30.
 */

public class ApplicationController extends Application {

    private static ApplicationController instance;
    public static ApplicationController getInstance() {return instance;}

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationController.instance=this;

        ApplicationController application = ApplicationController.getInstance();
        application.buildNetworkService("52.78.129.27",3000);
        networkService = ApplicationController.getInstance().getNetworkServe();
    }

    public NetworkService networkService;
    public NetworkService getNetworkServe(){return networkService;}

    private String baseUrl;

    public void buildNetworkService(String ip, int port){
        synchronized (ApplicationController.class){
            if(networkService==null){
                baseUrl=String.format("http://%s:%d",ip,port);
                Gson gson = new GsonBuilder().create();

                GsonConverterFactory factory = GsonConverterFactory.create(gson);

                Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(factory).build();
                networkService = retrofit.create(NetworkService.class);
            }
        }

    }

}
