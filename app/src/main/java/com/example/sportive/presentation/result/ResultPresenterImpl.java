package com.example.sportive.presentation.result;

import android.location.Location;

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

import javax.inject.Inject;

import io.reactivex.observers.DisposableMaybeObserver;
import timber.log.Timber;

/**
 * Created by Viet Hua on 4/10/2020
 */
public class ResultPresenterImpl implements ResultContract.Presenter {

    ResultContract.View mView;


    @Inject
    GetFieldBookingListUseCase getFieldBookingListUseCase;
    @Inject
    GetSportFieldByIdUseCase getSportFieldByIdUseCase;

    private SearchFieldConfig mSearchFieldConfig;

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
        mView = null;
    }

    @Override
    public void getFieldBookingList(SearchFieldConfig searchFieldConfig) {
        Timber.d("getFieldBookingList: %s", searchFieldConfig.toString());
        mSearchFieldConfig = searchFieldConfig;
        getFieldBookingListUseCase.execute(new GetFieldBookingListObserver(), new EmptyParam());

    }

    private List<String> handleOverlappingBooking(List<FieldBooking> fieldBookingList, long startTime, long finishTime) {
        Timber.d("handleOverlappingBooking: %s    , %s",startTime,finishTime);
        List<String> availableFieldId = new ArrayList<>();

        //REMOVE ALL OVERRLAPPED PLAY TIME
        Iterator<FieldBooking> iter = fieldBookingList.iterator();
        while (iter.hasNext()) {
            FieldBooking fieldBooking = iter.next();
            if (fieldBooking.getStartTime() < finishTime && fieldBooking.getFinishTime() > startTime) {
                Timber.e("REMOVED");
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

    private boolean checkIfSportFieldIsNearby(SportField sportField) {
        Timber.d("checkIfSportFieldIsNearby");
        Location target = new Location("target");
        Location anchor = new Location("anchor");
        target.setLatitude(sportField.getLatitude());
        target.setLongitude(sportField.getLongitude());
        anchor.setLatitude(mSearchFieldConfig.getLatitude());
        anchor.setLongitude(mSearchFieldConfig.getLongitude());
        if (anchor.distanceTo(target) < 2000) {
            return true;
        }
        return false;
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
            Timber.d("onComplete");
        }
    }

    private class GetSportFieldByIdObserver extends DisposableMaybeObserver<SportField> {
        @Override
        public void onSuccess(SportField sportField) {
            Timber.d("onSuccess: %s", sportField.toString());
            if (checkIfSportFieldIsNearby(sportField)) {
                mView.showAvailableSportFieldData(sportField);
            }
        }

        @Override
        public void onError(Throwable e) {
            Timber.e(e.getMessage());
        }

        @Override
        public void onComplete() {
            Timber.d("onComplete");
            mView.hideLoading();

        }
    }


}
