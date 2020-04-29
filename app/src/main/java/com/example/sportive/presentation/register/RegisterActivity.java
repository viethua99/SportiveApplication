package com.example.sportive.presentation.register;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sportive.R;
import com.example.sportive.presentation.base.BaseActivity;
import com.example.sportive.presentation.login.LoginActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import dagger.android.AndroidInjection;
import timber.log.Timber;

/**
 * Created by Viet Hua on 04/27/2020.
 */
public class RegisterActivity extends BaseActivity implements RegisterContract.View {

    @BindView(R.id.edt_email)
    EditText edtEmail;
    @BindView(R.id.edt_phone_number)
    EditText edtPhoneNumber;
    @BindView(R.id.edt_password)
    EditText edtPassword;
    @BindView(R.id.edt_username)
    EditText edtUsername;

    @Inject
    RegisterContract.Presenter presenter;

    public static void startRegisterActivity(AppCompatActivity activity) {
        Timber.d("startRegisterActivity");
        Intent intent = new Intent(activity, RegisterActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected int getResLayoutId() {
        return R.layout.activity_register;
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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Timber.d("onDestroy");
        presenter.dropView();
    }

    @OnClick(R.id.txt_sign_in_here)
    public void onSignInHereClick() {
        Timber.d("onSignInHereClick");
        LoginActivity.startLoginActivity(this);
    }

    @OnClick(R.id.btn_register)
    public void onRegisterClick() {
        Timber.d("onRegisterClick");
        String email = edtEmail.getText().toString();
        String phoneNumber = edtPhoneNumber.getText().toString();
        String password = edtPassword.getText().toString();
        String username = edtUsername.getText().toString();
        presenter.registerAccount(username,email, phoneNumber, password);
    }
}
