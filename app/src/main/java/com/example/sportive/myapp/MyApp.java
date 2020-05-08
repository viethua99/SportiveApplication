package com.example.sportive.myapp;

import android.app.Application;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

import com.example.remote.LatestEmailPrefs;
import com.example.sportive.BuildConfig;
import com.example.sportive.di.AppComponent;
import com.example.sportive.di.DaggerAppComponent;

import java.util.Locale;

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
        setFixedLanguageToVietnamese();
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

    //Set all datepicker / timerpick dialogs language to Vietnamese
    private void setFixedLanguageToVietnamese() {
        Timber.d("setFixedLanguageToVietnamese");
        Locale locale = new Locale("vi", "VN");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getApplicationContext().getResources().updateConfiguration(config, null);
    }

}
