package com.example.sportive.presentation.result;

import com.example.domain.interactor.fieldbooking.GetFieldBookingListUseCase;
import com.example.domain.interactor.sportfield.GetSportFieldByIdUseCase;
import com.example.domain.interactor.sportfield.GetSportFieldListUseCase;
import com.example.domain.model.EmptyParam;
import com.example.domain.model.FieldBooking;
import com.example.domain.model.SearchFieldConfig;
import com.example.domain.model.SportField;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import javax.inject.Inject;

import io.reactivex.observers.DisposableMaybeObserver;
import timber.log.Timber;

/**
 * Created by Viet Hua on 4/10/2020
 */
public class ResultPresenterImpl implements ResultContract.Presenter {

    ResultContract.View mView;
    private SearchFieldConfig mSearchFieldConfig;

    @Inject
    GetSportFieldListUseCase getSportFieldListUseCase;
    @Inject
    GetFieldBookingListUseCase getFieldBookingListUseCase;
    @Inject
    GetSportFieldByIdUseCase getSportFieldByIdUseCase;

    @Inject
    ResultPresenterImpl() {

    }

    @Override
    public void attachView(ResultContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        getFieldBookingListUseCase.dispose();
        getSportFieldByIdUseCase.dispose();
        getSportFieldListUseCase.dispose();
        mView = null;
    }

    @Override
    public void getFieldBookingList(SearchFieldConfig searchFieldConfig) {
        Timber.d("getFieldBookingList");
        mSearchFieldConfig = searchFieldConfig;
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
            List<String> availableUniqueFieldIdList = handleOverlappingBooking(fieldBookingList,
                    mSearchFieldConfig.getStartTime(),
                    mSearchFieldConfig.getFinishTime());
            for (String fieldId : availableUniqueFieldIdList) {
                getSportFieldByIdUseCase.execute(new GetSportFieldByIdObserver(), fieldId);
            }
        }

        @Override
        public void onError(Throwable e) {
            Timber.e(e.getMessage());
        }

        @Override
        public void onComplete() {

        }
    }

    private class GetSportFieldByIdObserver extends DisposableMaybeObserver<SportField> {
        @Override
        public void onSuccess(SportField sportField) {
            Timber.d("onSuccess: %s", sportField.toString());
            mView.addMoreSportFieldData(sportField);
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }

    private List<String> handleOverlappingBooking(List<FieldBooking> fieldBookingList, long startTime, long finishTime) {
        Timber.d("start time : %s  , finish time : %s", startTime, finishTime);
        List<String> availableFieldId = new ArrayList<>();

        //REMOVE ALL OVERRLAPPED PLAY TIME
        Iterator<FieldBooking> iter = fieldBookingList.iterator();
        while (iter.hasNext()) {
            FieldBooking fieldBooking = iter.next();
            if (fieldBooking.getStartTime() < finishTime && fieldBooking.getFinishTime() > startTime) {
                iter.remove();
            }
        }

        for (FieldBooking fieldBooking : fieldBookingList) {
            availableFieldId.add(fieldBooking.getFieldId());
        }
        //GET ALL UNIQUE FIELD ID
        Set<String> fieldBookingSet = new HashSet<>();
        fieldBookingSet.addAll(availableFieldId);
        List<String> availableUniqueFieldId = new ArrayList<>(fieldBookingSet);
        return availableUniqueFieldId;
    }
}
