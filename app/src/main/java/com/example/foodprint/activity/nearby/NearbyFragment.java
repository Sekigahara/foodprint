package com.example.foodprint.activity.nearby;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodprint.R;
import com.example.foodprint.activity.detail.DetailActivity;
import com.example.foodprint.base.BaseFragment;
import com.example.foodprint.model.restaurant.DataRestaurant;
import com.example.foodprint.model.restaurant.ParsedRestaurantData;
import com.example.foodprint.model.restaurant.Restaurant;
import com.example.foodprint.utils.recycler.RecycleViewAdapterNearby;
import com.example.foodprint.utils.utility.UtilProvider;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.LOCATION_SERVICE;

public class NearbyFragment extends BaseFragment<NearbyActivity, NearbyContract.Presenter> implements NearbyContract.View {
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    RecyclerView mRecyclerView;

    public NearbyFragment() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.activity_list_restaurant, container, false);
        mPresenter = new NearbyPresenter(this);
        mPresenter.start();

        LocationManager locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
        boolean statusOfGPS = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (statusOfGPS == true) {
            //LocationTrack locationTrack = new LocationTrack(activity);

            LocationManager mLocationManager;
            mLocationManager = (LocationManager) getContext().getSystemService(LOCATION_SERVICE);
            List<String> providers = mLocationManager.getProviders(true);
            Location bestLocation = null;
            for (String provider : providers) {
                Location l = mLocationManager.getLastKnownLocation(provider);
                if (l == null) {
                    continue;
                }
                if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                    // Found best last known location: %s", l);
                    bestLocation = l;
                }
            }

            final Double lat = bestLocation.getLatitude();//locationTrack.getLatitude();
            final Double lng = bestLocation.getLongitude();//locationTrack.getLongitude();

            UtilProvider.initKey("AIzaSyD0_sZhy7fJoeUcIGTmkTZbl5FNxYr2N-o");
            mPresenter.fetchMaps(1500, "false","restaurant",lat, lng,UtilProvider.getKey(), activity);
        }else{
            Toast.makeText(getActivity(), "Enable Your GPS", Toast.LENGTH_LONG).show();
            gotoNewTask(new Intent(activity, NearbyActivity.class));
        }

        return fragmentView;
    }

    public void viewNearby(Restaurant restaurant){
        mRecyclerView = fragmentView.findViewById(R.id.rvNearby);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(activity);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //parsing and sorting the data
        ArrayList<ParsedRestaurantData> data = mPresenter.sortByAscending(mPresenter.parseData(restaurant.getResults()));

        mAdapter = new RecycleViewAdapterNearby(data, getResources());
        mRecyclerView.setAdapter(mAdapter);

        ((RecycleViewAdapterNearby) mAdapter).setOnItemClickListener(new RecycleViewAdapterNearby.MyClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                ParsedRestaurantData selectedData = data.get(position);
                gotoNewTask(new Intent(activity, DetailActivity.class), selectedData);
            }
        });
    }

    public void gotoNewTask(Intent intent){
        startActivity(intent);
    }

    public void gotoNewTask(Intent intent, ParsedRestaurantData data){
        intent.putExtra("DATA", data);
        gotoNewTask(intent);
    }

    public void setPresenter(NearbyContract.Presenter presenter){
        mPresenter = presenter;
    }
}
