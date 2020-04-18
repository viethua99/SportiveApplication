package com.example.sportive.presentation.splash;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Viet Hua on 4/10/2020
 */

@Module
public abstract class SplashModule {
    @Binds
    public abstract SplashContract.Presenter splashPresenter(SplashPresenterImpl impl);
}
