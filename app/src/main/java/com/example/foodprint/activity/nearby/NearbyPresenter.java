package com.example.foodprint.activity.nearby;

import android.app.Activity;
import android.widget.Toast;

import com.example.foodprint.model.restaurant.DataRestaurant;
import com.example.foodprint.model.restaurant.ParsedRestaurantData;
import com.example.foodprint.utils.helper.ApiGoogleService;
import com.example.foodprint.model.restaurant.Restaurant;
import com.example.foodprint.utils.helper.UtilsApi;
import com.example.foodprint.utils.utility.UtilProvider;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.SphericalUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NearbyPresenter implements NearbyContract.Presenter {
    private final NearbyContract.View view;
    ApiGoogleService mGoogleApiService;

    public NearbyPresenter(NearbyContract.View view){
        this.view = view;
    }

    public void start(){

    }

    public void fetchMaps(int radius, String sensor ,String types, final Double lat, final Double lng, String key, final Activity activity) {
        String location = lat.toString() + ", " + lng.toString();
        UtilProvider.initLocationSession(lat ,lng);

        Toast.makeText(activity, "lat : " + lat.toString() + " lng : " + lng.toString(), Toast.LENGTH_LONG).show();
        mGoogleApiService = UtilsApi.getGoogleApiService();
        Call<Restaurant> call = mGoogleApiService.getNearestLaundry(types, location, radius, sensor, key);
        call.enqueue(new Callback<Restaurant>() {
            @Override
            public void onResponse(Call<Restaurant> call, Response<Restaurant> response) {
                if (response.isSuccessful() == true) {
                    Restaurant restaurant = response.body();
                    if (restaurant.getStatus().equals("OK")) {
                        Toast.makeText(activity, "Sort by Ascending", Toast.LENGTH_LONG).show();

                        view.viewNearby(restaurant);
                    } else {
                        //Toast.makeText(activity, restaurant.getStatus(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(activity, "Error2", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(activity, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public ArrayList<ParsedRestaurantData> parseData(List<DataRestaurant> data){
        ArrayList<ParsedRestaurantData> parsedData = new ArrayList<>();

        for(int i = 0 ; i < data.size(); i++){
            ParsedRestaurantData parsedRestaurantData = new ParsedRestaurantData();

            //set Distance
            Double fromLat = UtilProvider.getLocationUtil().getLocationLatitude();
            Double fromLng = UtilProvider.getLocationUtil().getLocationLongitude();
            LatLng from = new LatLng(fromLat, fromLng);

            Double toLat = data.get(i).getGeometry().getLocation().getLat();
            Double toLng = data.get(i).getGeometry().getLocation().getLng();
            LatLng to  = new LatLng(toLat, toLng);

            parsedRestaurantData.setDistance(countDistance(from, to));

            //set google id
            parsedRestaurantData.setIdGoogle(data.get(i).getPlaceId());
            parsedRestaurantData.setId(null);
            parsedRestaurantData.setName(data.get(i).getName());

            //Opening Hours
            if(data.get(i).getOpeningHours() == null)
                parsedRestaurantData.setOpen(false);
            else
                parsedRestaurantData.setOpen(data.get(i).getOpeningHours().getOpenNow());

            //set photo
            if(data.get(i).getPhotos() == null)
                parsedRestaurantData.setPhoto(null);
            else
                parsedRestaurantData.setPhoto(data.get(i).getPhotos().get(0).getPhotoReference());

            //set rating
            parsedRestaurantData.setRating(data.get(i).getRating());

            //append data
            parsedData.add(parsedRestaurantData);
        }

        return parsedData;
    }

    public ArrayList<ParsedRestaurantData> sortByAscending(ArrayList<ParsedRestaurantData> data){
        int arrayLength = data.size();

        for(int i = 0 ; i < arrayLength ; i++){
            int min = i;

            for(int k = i + 1 ; k < arrayLength; k++){
                if(data.get(k).getDistance() < data.get(min).getDistance())
                    min = k;
            }

            ParsedRestaurantData parsedRestaurantData = data.get(min);
            data.set(min, data.get(i));
            data.set(i, parsedRestaurantData);
        }

        return data;
    }

    private Double countDistance(LatLng from, LatLng to){
        return SphericalUtil.computeDistanceBetween(from ,to) / 1000;
    }
}
