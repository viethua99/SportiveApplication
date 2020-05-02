package com.example.sportive.presentation.booking;

import com.example.domain.model.FieldBooking;
import com.example.sportive.presentation.base.BasePresenter;
import com.example.sportive.presentation.base.BaseView;

import java.util.List;

/**
 * Created by Viet Hua on 04/27/2020.
 */
public interface BookingContract {
    interface View extends BaseView {
        void showNotLoginView();

        void showBookingList(List<FieldBooking> fieldBookingList);

        void showEmptyListMessage();
    }

    interface Presenter extends BasePresenter<View> {
        void checkIfUserIsLoggedIn();
    }
}
