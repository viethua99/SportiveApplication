package com.example.sportive.presentation.result;

import javax.inject.Inject;

/**
 * Created by Viet Hua on 4/10/2020
 */
public class ResultPresenterImpl implements ResultContract.Presenter {

    ResultContract.View mView;

    @Inject
    ResultPresenterImpl() {

    }

    @Override
    public void attachView(ResultContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
    }
}
