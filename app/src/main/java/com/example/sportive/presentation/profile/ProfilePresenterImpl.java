package com.example.sportive.presentation.profile;

import com.example.domain.interactor.auth.LogoutUseCase;
import com.example.domain.model.EmptyParam;
import com.example.sportive.di.SportiveManager;

import javax.inject.Inject;
import io.reactivex.observers.DisposableCompletableObserver;
import timber.log.Timber;

/**
 * Created by Viet Hua on 4/10/2020
 */
public class ProfilePresenterImpl implements ProfileContract.Presenter {
    ProfileContract.View mView;

    @Inject
    LogoutUseCase logoutUseCase;
    @Inject
    SportiveManager sportiveManager;

    @Inject
    ProfilePresenterImpl() {

    }


    @Override
    public void attachView(ProfileContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
    }

    @Override
    public void logout() {
        mView.showLoading();
        logoutUseCase.execute(new LogoutObserver(), new EmptyParam());
    }

    private class LogoutObserver extends DisposableCompletableObserver {
        @Override
        public void onComplete() {
            Timber.d("onComplete");
            mView.hideLoading();
            sportiveManager.setUserInfo(null);
            mView.showSignOutSuccess();
        }

        @Override
        public void onError(Throwable e) {
            Timber.e(e.getMessage());
        }
    }
}
