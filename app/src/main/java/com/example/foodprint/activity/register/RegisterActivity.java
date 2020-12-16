package com.example.foodprint.activity.register;

import com.example.foodprint.activity.register.RegisterFragment;
import com.example.foodprint.base.BaseFragmentHolderActivity;

public class RegisterActivity extends BaseFragmentHolderActivity {
    RegisterFragment registerFragment;
    protected void initializeFragment(){
        initializeView();

        registerFragment = new RegisterFragment();
        setCurrentFragment(registerFragment, true);
    }
}
