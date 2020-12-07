package com.example.foodprint.activity.register;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.example.foodprint.R;
import com.example.foodprint.activity.login.LoginContract;
import com.example.foodprint.model.user.User;
import com.example.foodprint.utils.helper.ApiGoogleService;
import com.example.foodprint.utils.session.SessionRepository;
import com.example.foodprint.utils.utility.UtilProvider;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterPresenter implements RegisterContract.Presenter{
    private final RegisterContract.View view;
    private final Context context;
    ApiGoogleService mApiService;
    private final SessionRepository sessionRepository;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    public RegisterPresenter(RegisterContract.View view, Context context, SessionRepository sessionRepository){
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

    public int onRegister(User user, String password_c){

        Toast.makeText(context, user.getPassword() + " = " + password_c, Toast.LENGTH_LONG).show();

        if(TextUtils.isEmpty(user.getEmail()))
            return 0;
        else if(user.getPassword().length() <= 6)
            return 1;
        else if(!user.getPassword().equals(password_c))
            return 2;
        else
            return 3;
    }

    @Override
    public void performRegister(User user, Activity activity) {
        if (user.getEmail().isEmpty() || user.getPassword().isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setMessage("Error Email or Password is Empty")
                    .setTitle("Sign Up Error")
                    .setPositiveButton(android.R.string.ok, null);
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            mFirebaseAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                    .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                //Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                //startActivity(intent);
                                view.gotoLogin();
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                                builder.setMessage(task.getException().getMessage())
                                        .setTitle("Error Sign Up")
                                        .setPositiveButton(android.R.string.ok, null);
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                        }
                    });
        }
    }
}