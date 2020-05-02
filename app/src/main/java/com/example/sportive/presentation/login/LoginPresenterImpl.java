package com.example.sportive.presentation.login;


import com.example.domain.interactor.auth.LoginUseCase;
import com.example.domain.model.UserInfo;
import com.example.sportive.di.SportiveManager;

import javax.inject.Inject;

import io.reactivex.observers.DisposableMaybeObserver;
import timber.log.Timber;

/**
 * Created by Viet Hua on 04/27/2020.
 */
public class LoginPresenterImpl implements LoginContract.Presenter {

    @Inject
    LoginUseCase loginUseCase;
    @Inject
    SportiveManager sportiveManager;

    LoginContract.View mView;

    @Inject
    public LoginPresenterImpl() {

    }


    @Override
    public void attachView(LoginContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
    }

    @Override
    public void login(String email, String password) {
        mView.showLoading();
        loginUseCase.execute(new LoginObserver(), new LoginUseCase.Param(email, password));
    }

    private class LoginObserver extends DisposableMaybeObserver<UserInfo> {
        @Override
        public void onSuccess(UserInfo userInfo) {
            mView.hideLoading();
            sportiveManager.setUserInfo(userInfo);
            sportiveManager.createUserSession();
            mView.showSuccessLogin();
        }

        @Override
        public void onError(Throwable e) {
            mView.hideLoading();
            Timber.e(e.getMessage());
            mView.showFailureLogin();
        }

        @Override
        public void onComplete() {
            Timber.d("onComplete");
        }
    }
}
