package com.example.foodprint.utils.utility;

import com.example.foodprint.model.restaurant.Location;

public class LocationUtil {
    Location location;

    public LocationUtil(Location location){
        this.location = location;
    }

    public Double getLocationLatitude(){
        return location.getLat();
    }

    public Double getLocationLongitude(){
        return location.getLng();
    }
}
