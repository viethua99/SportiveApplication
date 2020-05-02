package com.example.sportive.presentation.splash;

import com.example.sportive.presentation.base.BasePresenter;
import com.example.sportive.presentation.base.BaseView;

/**
 * Created by Viet Hua on 4/10/2020
 */
public interface SplashContract {
    interface View extends BaseView {
    }

    interface Presenter extends BasePresenter<View> {
        void checkLoggedIn();
    }

}
