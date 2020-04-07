package com.example.sportive.presentation.splash;

import android.os.Bundle;
import android.os.Handler;

import com.example.sportive.R;
import com.example.sportive.presentation.base.BaseActivity;
import com.example.sportive.presentation.main.MainActivity;

import androidx.annotation.Nullable;

/**
 * Created by Viet Hua on 4/7/2020
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                MainActivity.startMainActivity(SplashActivity.this);
            }
        }, 2000);
    }

    @Override
    protected int getResLayoutId() {
        return R.layout.activity_splash;
    }


}
