package com.example.sportive.presentation.location;

import javax.inject.Inject;

/**
 * Created by Viet Hua on 4/10/2020
 */
public class LocationPresenterImpl implements LocationContract.Presenter {
    LocationContract.View mView;

    @Inject
    public LocationPresenterImpl() {

    }

    @Override
    public void attachView(LocationContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
    }
}
