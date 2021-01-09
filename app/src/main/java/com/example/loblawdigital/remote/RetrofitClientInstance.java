package com.example.loblawdigital.remote;

import java.io.Serializable;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance implements Serializable {
    private static final String BASE_URL = "https://gist.githubusercontent.com/";
    private static volatile RetrofitClientInstance retrofitClientInstance;
    private final Retrofit retrofit;

    private RetrofitClientInstance() {
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

    public static RetrofitClientInstance get() {
        if (retrofitClientInstance == null) {
            synchronized (RetrofitClientInstance.class) {
                if (retrofitClientInstance == null) {
                    retrofitClientInstance = new RetrofitClientInstance();
                }
            }
        }
        return retrofitClientInstance;
    }

    public RemoteApi getClient() {
        return retrofit.create(RemoteApi.class);
    }
}
