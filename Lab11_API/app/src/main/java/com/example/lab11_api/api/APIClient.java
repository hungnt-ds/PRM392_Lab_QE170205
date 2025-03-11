package com.example.lab11_api.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static String baseURL = "https://67cbded03395520e6af67b5b.mockapi.io/";
    private static Retrofit retrofit;
    public static Retrofit getClient() {
        if(retrofit == null){
            retrofit= new Retrofit.Builder().baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
