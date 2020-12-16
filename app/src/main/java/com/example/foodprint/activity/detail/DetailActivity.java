package com.example.foodprint.activity.detail;

import com.example.foodprint.base.BaseFragmentHolderActivity;
import com.example.foodprint.model.restaurant.ParsedRestaurantData;

public class DetailActivity extends BaseFragmentHolderActivity {
    DetailFragment registerFragment;
    protected void initializeFragment(){
        initializeView();

        ParsedRestaurantData data = (ParsedRestaurantData) getIntent().getSerializableExtra("DATA");

        registerFragment = new DetailFragment(data);
        setCurrentFragment(registerFragment, true);
    }
}
