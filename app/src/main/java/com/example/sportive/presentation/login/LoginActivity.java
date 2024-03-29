package com.example.sportive.presentation.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.remote.LatestEmailPrefs;
import com.example.sportive.R;
import com.example.sportive.presentation.base.BaseActivity;
import com.example.sportive.presentation.register.RegisterActivity;
import com.example.sportive.presentation.resetpassword.ResetPasswordActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import dagger.android.AndroidInjection;
import timber.log.Timber;

/**
 * Created by Viet Hua on 04/27/2020.
 */
public class LoginActivity extends BaseActivity implements LoginContract.View {

    @BindView(R.id.edt_email)
    EditText edtEmail;
    @BindView(R.id.edt_password)
    EditText edtPassword;

    @Inject
    LoginContract.Presenter presenter;

    public static void startLoginActivity(AppCompatActivity activity) {
        Timber.d("startLoginActivity");
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected int getResLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.d("onCreate");
        AndroidInjection.inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Timber.d("onStart");
        presenter.attachView(this);
        edtEmail.setText(LatestEmailPrefs.getLatestEmail());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Timber.d("onDestroy");
        presenter.dropView();
    }

    @Override
    public void showSuccessLogin() {
        showToastMessage("Login Successfully");
        finish();
    }

    @Override
    public void showFailureLogin() {
        showToastMessage("Login Failed");
    }


    @OnClick(R.id.txt_sign_up_here)
    public void onSignUpHereClick() {
        RegisterActivity.startRegisterActivity(this);
        finish();
    }

    @OnClick(R.id.txt_forgot_password)
    public void onForgotPasswordClick() {
        ResetPasswordActivity.startResetPasswordActivity(this);
    }

    @OnClick(R.id.btn_login)
    public void onLoginClick() {
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();
        presenter.login(email, password);
    }
}
