package com.example.sportive.presentation.register;

import com.example.domain.interactor.auth.RegisterAccountUseCase;
import com.example.domain.model.UserInfo;
import com.example.sportive.di.SportiveManager;

import javax.inject.Inject;

import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableMaybeObserver;
import timber.log.Timber;

/**
 * Created by Viet Hua on 04/27/2020.
 */
public class RegisterPresenterImpl implements RegisterContract.Presenter {

    @Inject
    RegisterAccountUseCase registerAccountUseCase;
    @Inject
    SportiveManager sportiveManager;

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

    @Override
    public void registerAccount(String username, String email, String phoneNumber, String password) {
        mView.showLoading();
        registerAccountUseCase.execute(new RegisterAccountObserver(),
                new RegisterAccountUseCase.Param(username, email, phoneNumber, password));
    }

    private class RegisterAccountObserver extends DisposableMaybeObserver<UserInfo> {

        @Override
        public void onSuccess(UserInfo userInfo) {
            Timber.d("onSuccess: %s", userInfo);
            mView.hideLoading();
            sportiveManager.setUserInfo(userInfo);
            sportiveManager.createUserSession();
            mView.showMainScreen();

        }

        @Override
        public void onError(Throwable e) {
            mView.hideLoading();
            mView.showRegisterFailed();
            Timber.e(e.getMessage());
        }

        @Override
        public void onComplete() {
            Timber.d("onComplete");
        }
    }
}
