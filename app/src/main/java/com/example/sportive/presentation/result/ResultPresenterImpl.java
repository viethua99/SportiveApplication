package com.example.sportive.presentation.result;

import android.location.Location;

import com.example.domain.interactor.fieldbooking.GetFieldBookingListUseCase;
import com.example.domain.interactor.sportfield.GetSportFieldByIdUseCase;
import com.example.domain.interactor.sportfield.GetSportFieldIdListByDistrictUseCase;
import com.example.domain.interactor.sportfield.GetSportFieldIdListUseCase;
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
    @Inject
    GetSportFieldIdListUseCase getSportFieldIdListUseCase;

    @Inject
    GetSportFieldIdListByDistrictUseCase getSportFieldIdListByDistrictUseCase;

    private SearchFieldConfig mSearchFieldConfig;
    private List<String> mSportFieldIdList;
    private List<String> overlappedSportFieldIdList;

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

    private List<String> getOverlappedSportFieldList(List<FieldBooking> fieldBookingList, long startTime, long finishTime) {
        Timber.d("getOverlappedSportFieldList");
        Set<String> overlappedSportFieldSet = new HashSet<>();
        for (FieldBooking fieldBooking : fieldBookingList) {
            if (fieldBooking.getStartTime() < finishTime && fieldBooking.getFinishTime() > startTime) {
                overlappedSportFieldSet.add(fieldBooking.getFieldId());
            }
        }

        List<String> overlappedSportFieldList = new ArrayList<>(overlappedSportFieldSet);
        return overlappedSportFieldList;
    }

    private List<String> getAvailableSportFieldIdList() {
        Timber.d("getAvailableSportFieldIdList");
        List<String> availableSportFieldIdList;
        availableSportFieldIdList = new ArrayList<>(mSportFieldIdList);
        availableSportFieldIdList.addAll(overlappedSportFieldIdList);
        List<String> intersection = new ArrayList<>(mSportFieldIdList);
        intersection.retainAll(overlappedSportFieldIdList);
        availableSportFieldIdList.removeAll(intersection);

        return availableSportFieldIdList;
    }


    private boolean checkIfSportFieldIsNearby(SportField sportField) {
        Timber.d("checkIfSportFieldIsNearby");
        Location target = new Location("target");
        Location anchor = new Location("anchor");
        target.setLatitude(sportField.getLatitude());
        target.setLongitude(sportField.getLongitude());
        anchor.setLatitude(mSearchFieldConfig.getLatitude());
        anchor.setLongitude(mSearchFieldConfig.getLongitude());
        if (anchor.distanceTo(target) < 6000) {
            return true;
        }
        return false;
    }


    private class GetFieldBookingListObserver extends DisposableMaybeObserver<List<FieldBooking>> {
        @Override
        public void onSuccess(List<FieldBooking> fieldBookingList) {
            Timber.d("onSuccess: %s", fieldBookingList.toString());
            overlappedSportFieldIdList = getOverlappedSportFieldList(
                    fieldBookingList,
                    mSearchFieldConfig.getStartTime(),
                    mSearchFieldConfig.getFinishTime());
            Timber.d("overlapped: %s", overlappedSportFieldIdList);
            getSportFieldIdListUseCase.execute(new GetSportFieldIdListObserver(), new EmptyParam());
            Timber.e("TEST: %s", mSearchFieldConfig.getDistrictName());
            getSportFieldIdListByDistrictUseCase.execute(new GetSportFieldIdListByDistrictObserver(), mSearchFieldConfig.getDistrictName());
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
            mView.hideLoading();
            Timber.d("onSuccess: %s", sportField.toString());
            mView.showAvailableSportFieldData(sportField);
//            mView.showCannotFindAnyThing();
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

    private class GetSportFieldIdListObserver extends DisposableMaybeObserver<List<String>> {
        @Override
        public void onSuccess(List<String> sportFieldIdList) {
            Timber.d("onSuccess: %s", sportFieldIdList);
//            mSportFieldIdList = sportFieldIdList;
//            List<String> test = getAvailableSportFieldIdList();
//            for (String s : test) {
//                getSportFieldByIdUseCase.execute(new GetSportFieldByIdObserver(), s);
//            }
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

    private class GetSportFieldIdListByDistrictObserver extends DisposableMaybeObserver<List<String>> {
        @Override
        public void onSuccess(List<String> strings) {
            Timber.d("onSuccess : %s", strings);
            mView.hideLoading();
            if (strings.isEmpty()) {
                mView.showCannotFindAnyThing();
            } else {
                mSportFieldIdList = strings;
                List<String> test = getAvailableSportFieldIdList();
                for (String s : test) {
                    getSportFieldByIdUseCase.execute(new GetSportFieldByIdObserver(), s);
                }
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
}
