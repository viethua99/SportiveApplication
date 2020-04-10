package com.example.sportive.presentation.main;

import javax.inject.Inject;

/**
 * Created by Viet Hua on 4/10/2020
 */
public class MainPresenterImpl implements MainContract.Presenter {
    MainContract.View mView;

    @Inject
    public MainPresenterImpl() {

    }

    @Override
    public void attachView(MainContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
    }
}
