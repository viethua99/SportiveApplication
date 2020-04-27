package com.example.sportive.presentation.booking;

import javax.inject.Inject;

/**
 * Created by Viet Hua on 04/27/2020.
 */
public class BookingPresenterImpl implements BookingContract.Presenter {
    BookingContract.View mView;

    @Inject
    BookingPresenterImpl() {

    }

    @Override
    public void attachView(BookingContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
    }
}
