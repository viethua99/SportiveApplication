package com.example.sportive.presentation.login;

import com.example.sportive.presentation.base.BasePresenter;
import com.example.sportive.presentation.base.BaseView;

/**
 * Created by Viet Hua on 04/27/2020.
 */
public interface LoginContract {
    interface View extends BaseView{
        void showSuccessLogin();
        void showFailureLogin();
    }

    interface Presenter extends BasePresenter<View>{
        void login(String email,String password);
    }
}
