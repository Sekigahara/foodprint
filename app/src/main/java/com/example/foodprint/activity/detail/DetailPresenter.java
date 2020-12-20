package com.example.foodprint.activity.detail;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.example.foodprint.model.user.User;
import com.example.foodprint.utils.helper.ApiGoogleService;
import com.example.foodprint.utils.session.SessionRepository;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DetailPresenter implements DetailContract.Presenter{
    private final DetailContract.View view;
    private final Context context;
    ApiGoogleService mApiService;
    private final SessionRepository sessionRepository;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    public DetailPresenter(DetailContract.View view, Context context, SessionRepository sessionRepository){
        this.view = view;
        this.context = context;
        this.sessionRepository = sessionRepository;
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
    }

    public void start(){

    }

    public String onDirectionClick(Double latitude, Double longitude, String name){
        return "http://maps.google.com/maps?q=loc:" + latitude + "," + longitude +"(" + name + ")";
    }

}