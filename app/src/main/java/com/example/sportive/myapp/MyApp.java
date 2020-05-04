package com.example.sportive.myapp;

import android.app.Application;

import com.example.remote.LatestEmailPrefs;
import com.example.sportive.BuildConfig;
import com.example.sportive.di.AppComponent;
import com.example.sportive.di.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;
import timber.log.Timber;

/**
 * Created by Viet Hua on 4/7/2020
 */
public class MyApp extends Application implements HasAndroidInjector {
    @Inject
    DispatchingAndroidInjector<Object> androidInjector;


    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) Timber.plant(new Timber.DebugTree()); //Plant Timber
        initDagger();
        LatestEmailPrefs.initSharedPreferences(this);
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return androidInjector;
    }

    private void initDagger() {
        Timber.d("Init Dagger 2");
        AppComponent appComponent = DaggerAppComponent.builder().application(this).build();
        appComponent.inject(this);
    }

}
