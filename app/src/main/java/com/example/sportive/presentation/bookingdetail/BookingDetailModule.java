package com.example.sportive.presentation.bookingdetail;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Viet Hua on 05/06/2020.
 */

@Module
public abstract class BookingDetailModule {
    @Binds
    public abstract BookingDetailContract.Presenter bindBookingDetailPresenter(BookingDetailPresenterImpl impl);
}
