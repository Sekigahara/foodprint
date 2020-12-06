package com.example.foodprint.activity.nearby;

import android.os.Bundle;
import android.view.View;

import com.example.foodprint.base.BaseFragmentHolderActivity;

public class NearbyActivity extends BaseFragmentHolderActivity {
    NearbyFragment nearbyFragment;
    protected void initializeFragment(){
        initializeView();

        nearbyFragment = new NearbyFragment();
        setCurrentFragment(nearbyFragment, true);
    }

    public void setTitle(String title){

    }
}
