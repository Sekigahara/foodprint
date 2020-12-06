package com.example.foodprint.base;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.Toolbar;

import com.example.foodprint.R;


public abstract class BaseFragmentHolderActivity extends BaseActivity {

    protected RelativeLayout rlActivityFragmentHolder;

    @Override
    protected void initializeView() {
        setContentView(R.layout.base_activity);
        rlActivityFragmentHolder = (RelativeLayout) findViewById(R.id.rlActivityFragmentHolder);
    }
}
