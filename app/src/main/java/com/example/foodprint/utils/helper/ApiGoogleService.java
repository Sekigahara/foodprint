package com.example.foodprint.utils.helper;

import com.example.foodprint.model.restaurant.Restaurant;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiGoogleService {

    @GET("nearbysearch/json")
    Call<Restaurant> getNearestLaundry(
        @Query("type") String types,
        @Query("location") String location,
        @Query("radius") int radius,
        @Query("sensor") String sensor,
        @Query("key") String key
    );
}