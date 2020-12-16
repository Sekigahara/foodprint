package com.example.foodprint.activity.detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodprint.R;
import com.example.foodprint.activity.login.LoginActivity;
import com.example.foodprint.base.BaseFragment;
import com.example.foodprint.model.user.User;
import com.example.foodprint.utils.session.UserSessionRepositoryRepository;

public class DetailFragment extends BaseFragment<DetailActivity, DetailContract.Presenter> implements DetailContract.View {

    public DetailFragment(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.activity_register, container, false);
        mPresenter = new DetailPresenter(this, getActivity(), new UserSessionRepositoryRepository(getActivity()));
        mPresenter.start();


        return fragmentView;
    }

    public void gotoNewTask(Intent intent){
        startActivity(intent);
        activity.finish();
    }

    public void gotoLogin(){
        gotoNewTask(new Intent(activity, LoginActivity.class));
    }

    public void setPresenter(DetailContract.Presenter presenter){
        mPresenter = presenter;
    }
}
