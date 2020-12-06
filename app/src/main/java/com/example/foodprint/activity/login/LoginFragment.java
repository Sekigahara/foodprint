package com.example.foodprint.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodprint.R;
import com.example.foodprint.activity.nearby.NearbyActivity;
import com.example.foodprint.base.BaseFragment;
import com.example.foodprint.model.user.User;

public class LoginFragment extends BaseFragment<LoginActivity, LoginContract.Presenter> implements LoginContract.View {
    private Button btLogin;

    public LoginFragment(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.activity_login, container, false);
        mPresenter = new LoginPresenter(this, getActivity());
        mPresenter.start();

        btLogin = (Button) fragmentView.findViewById(R.id.bt_login);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoNewTask(new Intent(activity, NearbyActivity.class));
            }
        });

        return fragmentView;
    }

    public void setBtnLoginOnClick(final User user){
        int loginCode = mPresenter.onLogin(user);

        if(loginCode == 0)
            Toast.makeText(getActivity(), "You Must Enter Your Username", Toast.LENGTH_LONG).show();
        else if(loginCode == 1)
            Toast.makeText(getActivity(), "Password Must be Greater than 6 Characters", Toast.LENGTH_LONG).show();
        else {
            mPresenter.validateLogin(user, getActivity());
        }
    }

    public void gotoNewTask(Intent intent){
        startActivity(intent);
        activity.finish();
    }

    public void gotoDashboard(){
        gotoNewTask(new Intent(activity, NearbyActivity.class));
    }

    public void setPresenter(LoginContract.Presenter presenter){
        mPresenter = presenter;
    }
}
