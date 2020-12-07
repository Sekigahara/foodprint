package com.example.foodprint.activity.register;

import android.app.Activity;
import android.content.Intent;

import com.example.foodprint.base.BasePresenter;
import com.example.foodprint.base.BaseView;
import com.example.foodprint.model.user.User;

public interface RegisterContract {
    interface View extends BaseView<Presenter> {
        void gotoNewTask(Intent intent);
        void gotoLogin();
    }

    interface Presenter extends BasePresenter {
        int onRegister(User user, String password_c);
        void performRegister(User user, Activity activity);
    }
}
