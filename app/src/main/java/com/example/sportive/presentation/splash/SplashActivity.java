package com.example.sportive.presentation.splash;

import android.os.Bundle;
import android.os.Handler;

import com.example.sportive.R;
import com.example.sportive.presentation.base.BaseActivity;
import com.example.sportive.presentation.main.MainActivity;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import dagger.android.AndroidInjection;
import timber.log.Timber;

/**
 * Created by Viet Hua on 4/7/2020
 */
public class SplashActivity extends BaseActivity implements SplashContract.View {
    private static final long TRANSACTION_DELAY_TIME = 2000;

    @Inject
    SplashContract.Presenter presenter;

    @Override
    protected int getResLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.d("onCreate");
        AndroidInjection.inject(this);

        changeToMainActivityAfterDelayTime();

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

    private void changeToMainActivityAfterDelayTime() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Timber.d("changeToMainActivityAfterDelayTime");
                MainActivity.startMainActivity(SplashActivity.this);
            }
        }, TRANSACTION_DELAY_TIME);
    }

}
