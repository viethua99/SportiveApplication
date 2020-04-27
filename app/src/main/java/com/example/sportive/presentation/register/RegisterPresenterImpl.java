package com.example.sportive.presentation.register;

import javax.inject.Inject;

/**
 * Created by Viet Hua on 04/27/2020.
 */
public class RegisterPresenterImpl implements RegisterContract.Presenter {

    RegisterContract.View mView;

    @Inject
    public RegisterPresenterImpl() {

    }

    @Override
    public void attachView(RegisterContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
    }
}
