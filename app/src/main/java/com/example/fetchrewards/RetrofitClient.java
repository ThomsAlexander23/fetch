package com.example.fetchrewards;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static RetrofitClient instance = null;
    private final Api myAPi;

    private RetrofitClient(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.Base_URl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        myAPi = retrofit.create(Api.class);

    }

    public static synchronized RetrofitClient getInstance() {
        if (instance == null){
            instance = new RetrofitClient();
        }
        return instance;
    }

    public Api getMyAPi(){
        return myAPi;
    }
}
