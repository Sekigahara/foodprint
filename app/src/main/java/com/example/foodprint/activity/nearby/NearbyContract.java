package com.example.foodprint.activity.nearby;

import android.app.Activity;
import android.content.Intent;

import com.example.foodprint.base.BasePresenter;
import com.example.foodprint.base.BaseView;
import com.example.foodprint.model.restaurant.DataRestaurant;
import com.example.foodprint.model.restaurant.ParsedRestaurantData;
import com.example.foodprint.model.restaurant.Restaurant;

import java.util.ArrayList;
import java.util.List;

public interface NearbyContract {
    interface View extends BaseView<Presenter> {
        void gotoNewTask(Intent intent);
        void gotoNewTask(Intent intent, ParsedRestaurantData data);
        void viewNearby(Restaurant restaurant);
    }

    interface Presenter extends BasePresenter {
        void fetchMaps(int radius, String sensor, String types, Double lat, Double lng, String key, final Activity activity);
        ArrayList<ParsedRestaurantData> sortByAscending(ArrayList<ParsedRestaurantData> data);
        ArrayList<ParsedRestaurantData> parseData(List<DataRestaurant> data);
    }
}
