package com.example.foodprint.activity.nearby;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodprint.R;
import com.example.foodprint.base.BaseFragment;
import com.example.foodprint.model.restaurant.DataRestaurant;
import com.example.foodprint.model.restaurant.ParsedRestaurantData;
import com.example.foodprint.model.restaurant.Restaurant;
import com.example.foodprint.utils.recycler.RecycleViewAdapterNearby;
import com.example.foodprint.utils.utility.UtilProvider;

import java.util.ArrayList;
import java.util.List;

public class NearbyFragment extends BaseFragment<NearbyActivity, NearbyContract.Presenter> implements NearbyContract.View {
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    RecyclerView mRecyclerView;

    public NearbyFragment(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.activity_list_restaurant, container, false);
        mPresenter = new NearbyPresenter(this);
        mPresenter.start();

        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        boolean statusOfGPS = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if(statusOfGPS == true){
            LocationTrack locationTrack = new LocationTrack(activity);

            final Double lat = locationTrack.getLatitude();
            final Double lng = locationTrack.getLongitude();
            UtilProvider.initKey("AIzaSyD0_sZhy7fJoeUcIGTmkTZbl5FNxYr2N-o");

            mPresenter.fetchMaps(1500, "false","restaurant",lat, lng,UtilProvider.getKey(), activity);
        }else{
            Toast.makeText(getActivity(), "Enable Your GPS", Toast.LENGTH_LONG).show();
            gotoNewTask(new Intent(activity, NearbyActivity.class));
        }

        /*
        ((RecycleViewAdapterNearby) mAdapter).setOnItemClickListener(new RecycleViewAdapterNearby.MyClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                //int id = listOutlet.get(position).getId();
                Log.d("Dashboard", ">>>>" + position);

            }
        });
        */
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
    }

    public void gotoNewTask(Intent intent){
        startActivity(intent);
        activity.finish();
    }

    public void setPresenter(NearbyContract.Presenter presenter){
        mPresenter = presenter;
    }
}
