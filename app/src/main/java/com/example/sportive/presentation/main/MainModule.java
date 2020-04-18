package com.example.sportive.presentation.main;

import com.example.sportive.presentation.home.HomeContract;
import com.example.sportive.presentation.home.HomeFragment;
import com.example.sportive.presentation.home.HomePresenterImpl;
import com.example.sportive.presentation.profile.ProfileContract;
import com.example.sportive.presentation.profile.ProfileFragment;
import com.example.sportive.presentation.profile.ProfilePresenterImpl;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainModule {
    @ContributesAndroidInjector
    public abstract HomeFragment homeFragment();

    @ContributesAndroidInjector
    public abstract ProfileFragment profileFragment();


    @Binds
    public abstract MainContract.Presenter mainPresenter(MainPresenterImpl impl);

    @Binds
    public abstract HomeContract.Presenter homePresenter(HomePresenterImpl impl);

    @Binds
    public abstract ProfileContract.Presenter profilePresenter(ProfilePresenterImpl impl);
}
