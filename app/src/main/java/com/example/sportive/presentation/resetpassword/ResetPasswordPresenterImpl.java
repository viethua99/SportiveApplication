package com.example.sportive.presentation.resetpassword;

import com.example.domain.interactor.auth.ResetPasswordUseCase;

import javax.inject.Inject;

import io.reactivex.observers.DisposableCompletableObserver;
import timber.log.Timber;

/**
 * Created by Viet Hua on 05/04/2020.
 */
public class ResetPasswordPresenterImpl implements ResetPasswordContract.Presenter {

    ResetPasswordContract.View mView;

    @Inject
    ResetPasswordUseCase resetPasswordUseCase;

    @Inject
    public ResetPasswordPresenterImpl() {

    }

    @Override
    public void attachView(ResetPasswordContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
    }

    @Override
    public void resetPasswordFromEmail(String email) {
        Timber.d("resetPasswordFromEmail: %s", email);
        mView.showLoading();
        resetPasswordUseCase.execute(new ResetPasswordFromEmailObserver(), email);
    }

    private class ResetPasswordFromEmailObserver extends DisposableCompletableObserver {
        @Override
        public void onComplete() {
            Timber.d("onComplete");
            mView.hideLoading();
            mView.showResetPasswordConfirmMessage();
        }

        @Override
        public void onError(Throwable e) {
            mView.hideLoading();
            mView.showResetPasswordErrorMessage();
        }
    }

}
