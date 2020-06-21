package com.example.sportive.presentation.bookingdetail;

import com.example.domain.interactor.fieldbooking.DeleteBookingByIdUseCase;
import com.example.domain.interactor.fieldbooking.GetBookingDataByIdUseCase;
import com.example.domain.interactor.sportfield.GetSportFieldByIdUseCase;
import com.example.domain.model.BookingDetail;

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
    DeleteBookingByIdUseCase deleteBookingByIdUseCase;

    public BookingDetail mBookingDetail;

    @Inject
    public BookingDetailPresenterImpl() {

    }

    @Override
    public void attachView(BookingDetailContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        getBookingDataByIdUseCase.dispose();
        deleteBookingByIdUseCase.dispose();
        mView = null;
    }

    @Override
    public void retrieveBookingDataById(String bookingId) {
        Timber.d("retrieveBookingDataById: %s", bookingId);
        getBookingDataByIdUseCase.execute(new GetBookingDataByIdObserver(), bookingId);
    }

    @Override
    public void deleteBookingById(String bookingId) {
        Timber.d("deleteBookingById: %s", bookingId);
        deleteBookingByIdUseCase.execute(new DeleteBookingByIdObserver(), bookingId);
    }

    public BookingDetail getBookingDetail() {
       return mBookingDetail != null ? mBookingDetail : new BookingDetail();
    }


    private class GetBookingDataByIdObserver extends DisposableMaybeObserver<BookingDetail> {
        @Override
        public void onSuccess(BookingDetail bookingDetail) {
            Timber.d("onSuccess: %s", bookingDetail);
            mBookingDetail = bookingDetail;
            mView.showBookingDetail(bookingDetail);
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
