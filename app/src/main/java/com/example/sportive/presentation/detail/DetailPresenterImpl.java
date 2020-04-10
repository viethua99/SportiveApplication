package com.example.sportive.presentation.detail;

import javax.inject.Inject;

/**
 * Created by Viet Hua on 4/10/2020
 */
public class DetailPresenterImpl implements DetailContract.Presenter {
    DetailContract.View mView;

    @Inject
    public DetailPresenterImpl() {

    }

    @Override
    public void attachView(DetailContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
    }
}
