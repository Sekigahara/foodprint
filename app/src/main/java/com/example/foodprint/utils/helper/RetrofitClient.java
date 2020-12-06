package com.example.foodprint.utils.helper;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit = null;
    private static String baseUrlRetrofit = null;

    public static Retrofit getClient(String baseUrl){
        if(!baseUrl.equals(baseUrlRetrofit)) {
            baseUrlRetrofit = baseUrl;
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
