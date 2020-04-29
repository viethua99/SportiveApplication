package com.example.sportive.presentation.result;

import android.location.Location;

import com.example.domain.interactor.fieldbooking.GetFieldBookingListUseCase;
import com.example.domain.interactor.fieldbooking.SaveFieldBookingUseCase;
import com.example.domain.interactor.sportfield.GetSportFieldByIdUseCase;
import com.example.domain.interactor.sportfield.GetSportFieldIdListByDistrictUseCase;
import com.example.domain.interactor.sportfield.GetSportFieldIdListUseCase;
import com.example.domain.model.EmptyParam;
import com.example.domain.model.FieldBooking;
import com.example.domain.model.SearchFieldConfig;
import com.example.domain.model.SportField;
import com.example.sportive.di.SportiveManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableMaybeObserver;
import timber.log.Timber;
import utils.SportiveUtils;

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
    GetSportFieldIdListByDistrictUseCase getSportFieldIdListByDistrictUseCase;
    @Inject
    SaveFieldBookingUseCase saveFieldBookingUseCase;
    @Inject
    SportiveManager sportiveManager;

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
        getSportFieldIdListByDistrictUseCase.dispose();
        getSportFieldByIdUseCase.dispose();
        mView = null;
    }

    @Override
    public void getFieldBookingList(SearchFieldConfig searchFieldConfig) {
        Timber.d("getFieldBookingList: %s", searchFieldConfig.toString());
        mSearchFieldConfig = searchFieldConfig;
        getFieldBookingListUseCase.execute(new GetFieldBookingListObserver(), new EmptyParam());

    }

    @Override
    public void saveFieldBookingData(String sportFieldName, String fieldImg, String fieldId, int price) {
        if (sportiveManager.getUserInfo() != null) {
            FieldBooking fieldBooking = new FieldBooking(
                    mSearchFieldConfig.getStartTime(),
                    mSearchFieldConfig.getFinishTime(),
                    sportiveManager.getUserInfo().getUid(),
                    fieldId,
                    sportFieldName,
                    fieldImg,
                    price * mSearchFieldConfig.getDuration()
            );
            saveFieldBookingUseCase.execute(new SaveFieldBookingObserver(), fieldBooking);

        } else {
            mView.showNotLoggedIn();
        }
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

    private class GetFieldBookingListObserver extends DisposableMaybeObserver<List<FieldBooking>> {
        @Override
        public void onSuccess(List<FieldBooking> fieldBookingList) {
            Timber.d("onSuccess: %s", fieldBookingList.toString());
            overlappedSportFieldIdList = getOverlappedSportFieldList(
                    fieldBookingList,
                    mSearchFieldConfig.getStartTime(),
                    mSearchFieldConfig.getFinishTime());
            getSportFieldIdListByDistrictUseCase.execute(new GetSportFieldIdListByDistrictObserver(), mSearchFieldConfig.getDistrictName());
        }

        @Override
        public void onError(Throwable e) {
            Timber.e(e.getMessage());
        }

        @Override
        public void onComplete() {
            Timber.d("onComplete");
            getSportFieldIdListByDistrictUseCase.execute(new GetSportFieldIdListByDistrictObserver(), mSearchFieldConfig.getDistrictName());
        }
    }

    private class GetSportFieldByIdObserver extends DisposableMaybeObserver<SportField> {
        @Override
        public void onSuccess(SportField sportField) {
            mView.hideLoading();
            Timber.d("onSuccess: %s", sportField.toString());
            mView.showAvailableSportFieldData(sportField);
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

    private class GetSportFieldIdListByDistrictObserver extends DisposableMaybeObserver<List<String>> {
        @Override
        public void onSuccess(List<String> sportFieldIdList) {
            Timber.d("onSuccess : %s", sportFieldIdList);
            mView.hideLoading();
            List<String> availableSportFieldIdList = sportFieldIdList;
            if (sportFieldIdList.isEmpty()) {
                mView.showCannotFindAnyThing();
            } else {
                mSportFieldIdList = sportFieldIdList;
                if (overlappedSportFieldIdList != null) {
                    availableSportFieldIdList = getAvailableSportFieldIdList();
                }

                for (String sportFieldId : availableSportFieldIdList) {
                    getSportFieldByIdUseCase.execute(new GetSportFieldByIdObserver(), sportFieldId);
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

    private class SaveFieldBookingObserver extends DisposableCompletableObserver {
        @Override
        public void onComplete() {
            Timber.d("onComplete");
            mView.showSaveSuccessfully();
        }

        @Override
        public void onError(Throwable e) {
            Timber.e(e.getMessage());
        }
    }
}
