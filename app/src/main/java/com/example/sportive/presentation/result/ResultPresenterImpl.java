package com.example.sportive.presentation.result;

import com.example.domain.interactor.fieldbooking.GetFieldBookingListUseCase;
import com.example.domain.interactor.sportfield.GetSportFieldListUseCase;
import com.example.domain.model.EmptyParam;
import com.example.domain.model.FieldBooking;
import com.example.domain.model.SportField;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import javax.inject.Inject;

import io.reactivex.observers.DisposableMaybeObserver;
import timber.log.Timber;

/**
 * Created by Viet Hua on 4/10/2020
 */
public class ResultPresenterImpl implements ResultContract.Presenter {

    ResultContract.View mView;

    @Inject
    GetSportFieldListUseCase getSportFieldListUseCase;
    @Inject
    GetFieldBookingListUseCase getFieldBookingListUseCase;

    @Inject
    ResultPresenterImpl() {

    }

    @Override
    public void attachView(ResultContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
    }

    @Override
    public void getSportFieldList() {
        Timber.d("getSportFieldList");
        getSportFieldListUseCase.execute(new GetSportFieldListObserver(), new EmptyParam());
        getFieldBookingListUseCase.execute(new GetFieldBookingListObserver(), new EmptyParam());

    }

    private class GetSportFieldListObserver extends DisposableMaybeObserver<List<SportField>> {
        @Override
        public void onSuccess(List<SportField> sportFieldList) {
            Timber.d("onSuccess: %s", sportFieldList.toString());
            mView.showSportFieldList(sportFieldList);
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

    private class GetFieldBookingListObserver extends DisposableMaybeObserver<List<FieldBooking>> {
        @Override
        public void onSuccess(List<FieldBooking> fieldBookingList) {
            Timber.d("onSuccess: %s", fieldBookingList.toString());
            Timber.d("HANDLED: %s", handleOverlappingBooking(fieldBookingList, 100 , 600).toString());
        }

        @Override
        public void onError(Throwable e) {
            Timber.e(e.getMessage());
        }

        @Override
        public void onComplete() {

        }
    }

    private List<String> handleOverlappingBooking(List<FieldBooking> fieldBookingList, long start, long finish) {
        List<String> test = new ArrayList<>();

        Iterator<FieldBooking> iter = fieldBookingList.iterator();
        while (iter.hasNext()) {
            FieldBooking fieldBooking = iter.next();
            if (fieldBooking.getStartTime() < finish && fieldBooking.getFinishTime() > start) {
                iter.remove();
            }
        }

        for (FieldBooking fieldBooking : fieldBookingList) {
            test.add(fieldBooking.getFieldId());
            Timber.e(fieldBooking.toString());
        }
        return test;
    }
}
