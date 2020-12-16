package com.example.foodprint.activity.detail;

import android.app.Activity;
import android.content.Intent;

import com.example.foodprint.base.BasePresenter;
import com.example.foodprint.base.BaseView;
import com.example.foodprint.model.user.User;

public interface DetailContract {
    interface View extends BaseView<Presenter> {
        void gotoNewTask(Intent intent);
        void gotoLogin();
    }

    interface Presenter extends BasePresenter {

    }
}
