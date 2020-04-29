package com.example.sportive.presentation.register;

import com.example.sportive.presentation.base.BasePresenter;
import com.example.sportive.presentation.base.BaseView;

/**
 * Created by Viet Hua on 04/27/2020.
 */
public interface RegisterContract {
    interface View extends BaseView {
       void showMainScreen();
       void showRegisterFailed();
    }

    interface Presenter extends BasePresenter<View> {
        void registerAccount(String username,String email, String phoneNumber, String password);
    }

}
