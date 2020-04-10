package com.example.sportive.presentation.splash;

import javax.inject.Inject;

/**
 * Created by Viet Hua on 4/10/2020
 */
public class SplashPresenterImpl implements SplashContract.Presenter {

    SplashContract.View mView;

    @Inject
    SplashPresenterImpl() {

    }

    @Override
    public void attachView(SplashContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
    }
}
