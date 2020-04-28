package com.example.sportive.presentation.booking;

import com.example.sportive.di.SportiveManager;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by Viet Hua on 04/27/2020.
 */
public class BookingPresenterImpl implements BookingContract.Presenter {
    BookingContract.View mView;

    @Inject
    SportiveManager sportiveManager;

    @Inject
    BookingPresenterImpl() {

    }

    @Override
    public void attachView(BookingContract.View view) {
        mView = view;
        if(sportiveManager.getUserInfo() !=null){
            String uid =  sportiveManager.getUserInfo().getUid();
            Timber.d("%s",uid);
        } else {
            Timber.d("NULL");
        }
    }

    @Override
    public void dropView() {
        mView = null;
    }
}
