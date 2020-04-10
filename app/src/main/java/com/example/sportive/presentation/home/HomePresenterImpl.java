package com.example.sportive.presentation.home;

import javax.inject.Inject;

/**
 * Created by Viet Hua on 4/10/2020
 */
public class HomePresenterImpl implements HomeContract.Presenter {
    HomeContract.View mView;

    @Inject
    public HomePresenterImpl() {

    }

    @Override
    public void attachView(HomeContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
    }
}
