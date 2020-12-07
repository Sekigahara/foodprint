package com.example.foodprint.utils.helper;

public class UtilsApi {
    public static final String BASE_MAPS_URL_API="https://maps.googleapis.com/maps/api/place/";

    public static ApiGoogleService getGoogleApiService(){
        return RetrofitClient.getClient(BASE_MAPS_URL_API).create(ApiGoogleService.class);
    }
}