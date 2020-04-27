package com.example.sportive.presentation.login;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.sportive.R;
import com.example.sportive.presentation.base.BaseActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import timber.log.Timber;

/**
 * Created by Viet Hua on 04/27/2020.
 */
public class LoginActivity extends BaseActivity implements LoginContract.View {

    @Inject
    LoginContract.Presenter presenter;

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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Timber.d("onDestroy");
        presenter.dropView();
    }
}
