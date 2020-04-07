package com.example.sportive.presentation;

import android.os.Bundle;

import com.example.sportive.R;
import com.example.sportive.presentation.base.BaseActivity;

import androidx.annotation.Nullable;
import timber.log.Timber;

/**
 * Created By Viet Hua on 4/7/2020
 */
public class MainActivity extends BaseActivity {

    @Override
    protected int getResLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.d("onCreated");

    }
}
