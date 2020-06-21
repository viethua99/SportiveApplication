package com.example.sportive.presentation.bookingdetail;

import com.example.domain.model.BookingDetail;
import com.example.domain.model.FieldBooking;
import com.example.domain.model.SportField;
import com.example.sportive.presentation.base.BasePresenter;
import com.example.sportive.presentation.base.BaseView;

/**
 * Created by Viet Hua on 05/06/2020.
 */
public interface BookingDetailContract {
    interface View extends BaseView {
        void showBookingDetail(BookingDetail bookingDetail);
        void showDeleteBookingSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void retrieveBookingDataById(String bookingId);
        void deleteBookingById(String bookingId);
        BookingDetail getBookingDetail();
    }
}
