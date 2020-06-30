package com.example.sportive.presentation.edituser;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by Viet Hua on 06/30/2020.
 */
public class EditUserPresenterImpl implements EditUserContract.Presenter {

    private EditUserContract.View mView;

    @Inject
    public EditUserPresenterImpl() {

    }

    @Override
    public void attachView(EditUserContract.View view) {
        Timber.d("attachView");
        this.mView = view;
    }

    @Override
    public void dropView() {
        Timber.d("dropView");
        this.mView = null;
    }
}
