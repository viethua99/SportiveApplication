package com.example.sportive.presentation.resetpassword;

import com.example.sportive.presentation.base.BasePresenter;
import com.example.sportive.presentation.base.BaseView;

/**
 * Created by Viet Hua on 05/04/2020.
 */
public interface ResetPasswordContract {
    interface View extends BaseView {
        void showResetPasswordConfirmMessage();

        void showResetPasswordErrorMessage();
    }

    interface Presenter extends BasePresenter<View> {
        void resetPasswordFromEmail(String email);
    }
}
