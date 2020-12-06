package com.example.foodprint.activity.register;

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

public class RegisterFragment extends BaseFragment<RegisterActivity, RegisterContract.Presenter> implements RegisterContract.View {
    private Button btRegister;
    private Button btBack;
    private EditText etEmail;
    private EditText etUsername;
    private EditText etPassword;
    private EditText etPassword_c;

    public RegisterFragment(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.activity_register, container, false);
        mPresenter = new RegisterPresenter(this, getActivity(), new UserSessionRepositoryRepository(getActivity()));
        mPresenter.start();

        etEmail = fragmentView.findViewById(R.id.et_email);
        etUsername = fragmentView.findViewById(R.id.et_username);
        etPassword = fragmentView.findViewById(R.id.et_password);
        etPassword_c = fragmentView.findViewById(R.id.et_confirm_password);
        btRegister = (Button) fragmentView.findViewById(R.id.bt_register);
        btBack = (Button) fragmentView.findViewById(R.id.bt_back);

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User(etEmail.getText().toString(), etPassword.getText().toString());
                user.setUsername(etUsername.getText().toString());
                //gotoNewTask(new Intent(activity, NearbyActivity.class));
                setBtnRegisterOnClick(user);
            }
        });

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoLogin();
            }
        });

        return fragmentView;
    }

    public void setBtnRegisterOnClick(final User user){
        int registerCode = mPresenter.onRegister(user, etPassword_c.getText().toString());

        if(registerCode == 0)
            Toast.makeText(getActivity(), "You Must Enter Your Username", Toast.LENGTH_LONG).show();
        else if(registerCode == 1)
            Toast.makeText(getActivity(), "Password Must be Greater than 6 Characters", Toast.LENGTH_LONG).show();
        else if(registerCode == 2)
            Toast.makeText(getActivity(), "Password and Password Confirmation is not the Same", Toast.LENGTH_LONG).show();
        else {
            mPresenter.performRegister(user, getActivity());
        }
    }

    public void gotoNewTask(Intent intent){
        startActivity(intent);
        activity.finish();
    }

    public void gotoLogin(){
        gotoNewTask(new Intent(activity, LoginActivity.class));
    }

    public void setPresenter(RegisterContract.Presenter presenter){
        mPresenter = presenter;
    }
}
