package com.example.loblawdigital.remote;

import com.example.loblawdigital.model.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RemoteApi {

    @GET("r2vq/2ac197145db3f6cdf1a353feb744cf8e/raw/b1e722f608b00ddde138a0eef2261c6ffc8b08d7/cart.json")
    Call<ApiResponse> getData();
}
