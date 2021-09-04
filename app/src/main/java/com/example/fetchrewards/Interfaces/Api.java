package com.example.fetchrewards.Interfaces;

import com.example.fetchrewards.Models.Result;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface Api {
    String Base_URl = "https://fetch-hiring.s3.amazonaws.com/";

    @GET("hiring.json")
    Call<List<Result>> getRequestData();
}
