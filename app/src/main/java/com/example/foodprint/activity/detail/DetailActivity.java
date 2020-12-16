package com.example.foodprint.activity.detail;

import com.example.foodprint.base.BaseFragmentHolderActivity;

public class DetailActivity extends BaseFragmentHolderActivity {
    DetailFragment registerFragment;
    protected void initializeFragment(){
        initializeView();

        registerFragment = new DetailFragment();
        setCurrentFragment(registerFragment, false);
    }
}
