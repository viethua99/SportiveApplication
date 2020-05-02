package com.example.sportive.presentation.splash;

import com.example.domain.interactor.auth.CheckLoggedInUseCase;
import com.example.domain.model.EmptyParam;
import com.example.domain.model.IsLogged;
import com.example.domain.model.UserInfo;
import com.example.sportive.di.SportiveManager;

import javax.inject.Inject;

import io.reactivex.observers.DisposableMaybeObserver;
import timber.log.Timber;

/**
 * Created by Viet Hua on 4/10/2020
 */
public class SplashPresenterImpl implements SplashContract.Presenter {

    SplashContract.View mView;

    @Inject
    SportiveManager sportiveManager;
    @Inject
    CheckLoggedInUseCase checkLoggedInUseCase;

    @Inject
    SplashPresenterImpl() {

    }

    @Override
    public void attachView(SplashContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
    }

    @Override
    public void checkLoggedIn() {
        checkLoggedInUseCase.execute(new CheckLoggedInObserver(), new EmptyParam());
    }

    private class CheckLoggedInObserver extends DisposableMaybeObserver<UserInfo> {
        @Override
        public void onSuccess(UserInfo userInfo) {
            Timber.d("onSuccess: %s", userInfo.toString());
            sportiveManager.setUserInfo(userInfo);
            sportiveManager.createUserSession();
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
