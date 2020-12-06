package com.example.foodprint.activity.login;

import android.app.Activity;
import android.content.Intent;

import com.example.foodprint.base.BasePresenter;
import com.example.foodprint.base.BaseView;
import com.example.foodprint.model.user.User;

public interface LoginContract {
    interface View extends BaseView<Presenter> {
        void gotoNewTask(Intent intent);
        void gotoDashboard();
    }

    interface Presenter extends BasePresenter {
        int onLogin(User user);
        void validateLogin(User user, Activity activity);
    }
}
