package com.example.lab11_api.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TraineeRepository {
    public static  TraineeService getTraineeService(){
        return APIClient.getClient().create(TraineeService.class);
    }
}
