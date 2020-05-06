package com.example.sportive.presentation.bookingdetail;

import com.example.domain.model.FieldBooking;
import com.example.domain.model.SportField;
import com.example.sportive.presentation.base.BasePresenter;
import com.example.sportive.presentation.base.BaseView;

/**
 * Created by Viet Hua on 05/06/2020.
 */
public interface BookingDetailContract {
    interface View extends BaseView {
        void showBookingData(FieldBooking fieldBooking);
        void showDeleteBookingSuccess();
        void showFieldData(SportField sportField);
    }

    interface Presenter extends BasePresenter<View> {
        void getBookingDataById(String bookingId);

        void deleteBookingById(String bookingId);
    }
}
