package com.example.foodprint.activity.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.foodprint.model.user.User;
import com.example.foodprint.utils.helper.ApiGoogleService;
import com.example.foodprint.utils.helper.UtilsApi;
import com.example.foodprint.utils.utility.UtilProvider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginContract.Presenter{
    private final LoginContract.View view;
    private final Context context;
    ApiGoogleService mApiService;

    public LoginPresenter(LoginContract.View view, Context context){
        this.view = view;
        this.context = context;
    }

    public void start(){
        UtilProvider.initUserSession(this.context);

        if(UtilProvider.getUserSessionUtil().getSession() != null){
            view.gotoDashboard();   //jika sudah login langsung masuk dashboard
        }
    }

    public int onLogin(User user){
        if(TextUtils.isEmpty(user.getUsername()))
            return 0;
        else if(user.getPassword().length() <= 6)
            return 1;
        else
            return 2;
    }

    public void validateLogin(User user, Activity activity){
        view.gotoDashboard();
    }
}