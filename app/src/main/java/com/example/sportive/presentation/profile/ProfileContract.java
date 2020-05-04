package com.example.sportive.presentation.profile;

import com.example.domain.model.UserInfo;
import com.example.sportive.presentation.base.BasePresenter;
import com.example.sportive.presentation.base.BaseView;

/**
 * Created by Viet Hua on 4/10/2020
 */
public interface ProfileContract {
    interface View extends BaseView {
        void showSignOutSuccess();

        void showNotLoggedInView();

        void showUserInfo(UserInfo userInfo);
    }

    interface Presenter extends BasePresenter<View> {
        void checkIfUserIsLoggedIn();

        void logout();
    }
}
