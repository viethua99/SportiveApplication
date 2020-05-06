package com.example.sportive.presentation.bookingdetail;

import com.example.domain.interactor.fieldbooking.DeleteBookingByIdUseCase;
import com.example.domain.interactor.fieldbooking.GetBookingDataByIdUseCase;
import com.example.domain.interactor.sportfield.GetSportFieldByIdUseCase;
import com.example.domain.model.FieldBooking;
import com.example.domain.model.SportField;

import javax.inject.Inject;

import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableMaybeObserver;
import timber.log.Timber;

/**
 * Created by Viet Hua on 05/06/2020.
 */
public class BookingDetailPresenterImpl implements BookingDetailContract.Presenter {

    BookingDetailContract.View mView;

    @Inject
    GetBookingDataByIdUseCase getBookingDataByIdUseCase;
    @Inject
    GetSportFieldByIdUseCase getSportFieldByIdUseCase;
    @Inject
    DeleteBookingByIdUseCase deleteBookingByIdUseCase;

    @Inject
    public BookingDetailPresenterImpl() {

    }

    @Override
    public void attachView(BookingDetailContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
    }

    @Override
    public void getBookingDataById(String bookingId) {
        Timber.d("getBookingDataById: %s", bookingId);
        getBookingDataByIdUseCase.execute(new GetBookingDataByIdObserver(), bookingId);
    }

    @Override
    public void deleteBookingById(String bookingId) {
        Timber.d("deleteBookingById: %s", bookingId);
        deleteBookingByIdUseCase.execute(new DeleteBookingByIdObserver(), bookingId);
    }

    private class GetBookingDataByIdObserver extends DisposableMaybeObserver<FieldBooking> {
        @Override
        public void onSuccess(FieldBooking fieldBooking) {
            Timber.d("onSuccess: %s", fieldBooking);
            mView.showBookingData(fieldBooking);
            getSportFieldByIdUseCase.execute(new GetSportFieldByIdObserver(), fieldBooking.getFieldId());
        }

        @Override
        public void onError(Throwable e) {
            Timber.e(e.getMessage());
        }

        @Override
        public void onComplete() {
            Timber.d("onComplete");
        }
    }

    private class GetSportFieldByIdObserver extends DisposableMaybeObserver<SportField> {
        @Override
        public void onSuccess(SportField sportField) {
            Timber.d("onSuccess: %s", sportField);
            mView.showFieldData(sportField);
        }

        @Override
        public void onError(Throwable e) {
            Timber.e(e.getMessage());
        }

        @Override
        public void onComplete() {
            Timber.e("onComplete");
        }
    }

    private class DeleteBookingByIdObserver extends DisposableCompletableObserver {
        @Override
        public void onComplete() {
            Timber.d("onComplete");
            mView.showDeleteBookingSuccess();
        }

        @Override
        public void onError(Throwable e) {
            Timber.e(e.getMessage());
        }
    }
}
