package com.example.sportive.presentation.map;

import javax.inject.Inject;

/**
 * Created by Viet Hua on 04/19/2020.
 */
public class MapPresenterImpl implements MapContract.Presenter {

    MapContract.View mView;

    @Inject
    public MapPresenterImpl() {

    }

    @Override
    public void attachView(MapContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
    }
}
