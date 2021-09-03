package com.example.fetchrewards;

import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface Api {
    String Base_URl = "https://fetch-hiring.s3.amazonaws.com/";

    @GET("hiring.json")
    Call<List<Results>> getRequestData();
}
