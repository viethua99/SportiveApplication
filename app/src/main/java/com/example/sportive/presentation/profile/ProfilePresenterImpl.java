package com.example.sportive.presentation.profile;

import javax.inject.Inject;

/**
 * Created by Viet Hua on 4/10/2020
 */
public class ProfilePresenterImpl implements ProfileContract.Presenter {
    ProfileContract.View mView;

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
}
