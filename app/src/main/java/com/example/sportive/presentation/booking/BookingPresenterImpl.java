package com.example.sportive.presentation.booking;

import com.example.domain.interactor.fieldbooking.GetFieldBookingListByIdUseCase;
import com.example.domain.model.FieldBooking;
import com.example.sportive.di.SportiveManager;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableMaybeObserver;
import timber.log.Timber;

/**
 * Created by Viet Hua on 04/27/2020.
 */
public class BookingPresenterImpl implements BookingContract.Presenter {
    BookingContract.View mView;

    @Inject
    SportiveManager sportiveManager;
    @Inject
    GetFieldBookingListByIdUseCase getFieldBookingListByIdUseCase;

    @Inject
    BookingPresenterImpl() {

    }

    @Override
    public void attachView(BookingContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        getFieldBookingListByIdUseCase.dispose();
        mView = null;
    }

    @Override
    public void checkIfUserIsLoggedIn() {
        Timber.d("checkIfUserIsLoggedIn");
        if (sportiveManager.getUserInfo() != null) {
            getFieldBookingListByIdUseCase.execute(new GetFieldBookingListByIdObserver(),
                    sportiveManager.getUserInfo().getUid());
        } else {
            Timber.e("USER NULL");
            mView.showNotLoginView();
        }
    }

    private class GetFieldBookingListByIdObserver extends DisposableMaybeObserver<List<FieldBooking>> {

        @Override
        public void onSuccess(List<FieldBooking> fieldBookings) {
            Timber.e("onSuccess: %s",fieldBookings);
            mView.showBookingList();
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
}
