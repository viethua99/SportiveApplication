package com.example.sportive.presentation.login;


import com.example.domain.interactor.auth.LoginUseCase;
import com.example.domain.model.UserInfo;

import javax.inject.Inject;

import io.reactivex.observers.DisposableMaybeObserver;
import timber.log.Timber;

/**
 * Created by Viet Hua on 04/27/2020.
 */
public class LoginPresenterImpl implements LoginContract.Presenter {

    @Inject
    LoginUseCase loginUseCase;

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
        loginUseCase.execute(new LoginObserver(), new LoginUseCase.Param(email, password));
    }

    private class LoginObserver extends DisposableMaybeObserver<UserInfo> {
        @Override
        public void onSuccess(UserInfo userInfo) {

            Timber.d("onSuccess: %s", userInfo);
        }

        @Override
        public void onError(Throwable e) {
            Timber.e(e.getMessage());
        }

        @Override
        public void onComplete() {
            Timber.d("onComplete");
        }
    }
}
