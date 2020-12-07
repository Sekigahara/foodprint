package com.example.foodprint.activity.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.example.foodprint.R;
import com.example.foodprint.model.user.User;
import com.example.foodprint.utils.helper.ApiGoogleService;
import com.example.foodprint.utils.helper.UtilsApi;
import com.example.foodprint.utils.session.SessionRepository;
import com.example.foodprint.utils.utility.UtilProvider;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginContract.Presenter{
    private final LoginContract.View view;
    private final Context context;
    ApiGoogleService mApiService;
    private final SessionRepository sessionRepository;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    public LoginPresenter(LoginContract.View view, Context context, SessionRepository sessionRepository){
        this.view = view;
        this.context = context;
        this.sessionRepository = sessionRepository;
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
    }

    public void start(){
        /*UtilProvider.initUserSession(this.context);

        if(UtilProvider.getUserSessionUtil().getSession() != null){
            view.gotoDashboard();   //jika sudah login langsung masuk dashboard
        }*/
    }

    public int onLogin(User user){
        if(TextUtils.isEmpty(user.getEmail()))
            return 0;
        else if(user.getPassword().length() <= 6)
            return 1;
        else
            return 2;
    }

    public void validateLogin(User user, Activity activity){
        mFirebaseAuth.signInWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            sessionRepository.setSessionData(user);
                            view.gotoDashboard();
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                            builder.setMessage(task.getException().getMessage())
                                    .setTitle("Error")
                                    .setPositiveButton(android.R.string.ok, null);
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    }
                });

//        view.gotoDashboard();
    }
}