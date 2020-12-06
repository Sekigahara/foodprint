package com.example.foodprint.activity.login;

import android.view.View;

import com.example.foodprint.base.BaseFragmentHolderActivity;

public class LoginActivity extends BaseFragmentHolderActivity {
    LoginFragment loginFragment;
    protected void initializeFragment(){
        initializeView();

        loginFragment = new LoginFragment();
        setCurrentFragment(loginFragment, false);
    }
}