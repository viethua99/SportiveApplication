package com.example.sportive.di;

import com.example.sportive.presentation.detail.DetailActivity;
import com.example.sportive.presentation.detail.DetailModule;
import com.example.sportive.presentation.location.LocationActivity;
import com.example.sportive.presentation.location.LocationModule;
import com.example.sportive.presentation.login.LoginActivity;
import com.example.sportive.presentation.main.MainActivity;
import com.example.sportive.presentation.main.MainModule;
import com.example.sportive.presentation.map.MapActivity;
import com.example.sportive.presentation.map.MapModule;
import com.example.sportive.presentation.register.RegisterActivity;
import com.example.sportive.presentation.register.RegisterModule;
import com.example.sportive.presentation.result.ResultActivity;
import com.example.sportive.presentation.result.ResultModule;
import com.example.sportive.presentation.splash.SplashActivity;
import com.example.sportive.presentation.splash.SplashModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Viet Hua on 4/7/2020
 */
@Module
public abstract class AppBindingModule {
    @ContributesAndroidInjector(modules = DetailModule.class)
    public abstract DetailActivity detailActivity();

    @ContributesAndroidInjector(modules = MainModule.class)
    public abstract MainActivity mainActivity();

    @ContributesAndroidInjector(modules = LocationModule.class)
    public abstract LocationActivity locationActivity();

    @ContributesAndroidInjector(modules = ResultModule.class)
    public abstract ResultActivity resultActivity();

    @ContributesAndroidInjector(modules = SplashModule.class)
    public abstract SplashActivity splashActivity();

    @ContributesAndroidInjector(modules = MapModule.class)
    public abstract MapActivity mapActivity();

    @ContributesAndroidInjector(modules = RegisterModule.class)
    public abstract RegisterActivity registerActivity();

    @ContributesAndroidInjector(modules = LoginActivity.class)
    public abstract LoginActivity loginActivity();

}
