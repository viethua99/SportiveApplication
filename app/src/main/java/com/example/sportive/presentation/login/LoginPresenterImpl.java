package com.example.sportive.presentation.login;

import javax.inject.Inject;

/**
 * Created by Viet Hua on 04/27/2020.
 */
public class LoginPresenterImpl implements LoginContract.Presenter {


    @Inject
    public LoginContract.View mView;

    @Override
    public void attachView(LoginContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
    }
}
