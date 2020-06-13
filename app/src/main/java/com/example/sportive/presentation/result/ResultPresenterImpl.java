package com.example.sportive.presentation.result;

import com.example.domain.interactor.fieldbooking.GetAvailableFieldIdListUseCase;
import com.example.domain.interactor.fieldbooking.SaveFieldBookingUseCase;
import com.example.domain.interactor.sportfield.GetSportFieldUseCase;
import com.example.domain.model.FieldBooking;
import com.example.domain.model.SearchFieldConfig;
import com.example.domain.model.SportField;
import com.example.sportive.di.SportiveManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.observers.DisposableObserver;
import timber.log.Timber;

/**
 * Created by Viet Hua on 4/10/2020
 */
public class ResultPresenterImpl implements ResultContract.Presenter {
    ResultContract.View mView;


    @Inject
    SportiveManager sportiveManager;
    @Inject
    GetSportFieldUseCase getSportFieldUseCase;
    @Inject
    SaveFieldBookingUseCase saveFieldBookingUseCase;
    @Inject
    GetAvailableFieldIdListUseCase getAvailableFieldIdListUseCase;


    private SearchFieldConfig mSearchFieldConfig;
    private Map<String, Integer> EmptyFieldCountMap = new HashMap<>();
    private List<SportField> sportFieldList = new ArrayList<>();

    @Inject
    ResultPresenterImpl() {

    }

    @Override
    public void attachView(ResultContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        getSportFieldUseCase.dispose();
        saveFieldBookingUseCase.dispose();
        getAvailableFieldIdListUseCase.dispose();
        mView = null;
    }

    @Override
    public void getAvailableSportField(SearchFieldConfig searchFieldConfig) {
        Timber.d("getAvailableSportField: %s", searchFieldConfig.toString());
        mSearchFieldConfig = searchFieldConfig;
        getAvailableFieldIdListUseCase.execute(new GetAvailableFieldIdListObserver(), searchFieldConfig);
    }

    @Override
    public void saveFieldBookingData(String sportFieldName, String fieldImg, String fieldId, int price) {
        if (sportiveManager.getUserInfo() != null) {
            FieldBooking fieldBooking = new FieldBooking(
                    mSearchFieldConfig.getStartTime(),
                    mSearchFieldConfig.getFinishTime(),
                    mSearchFieldConfig.getDuration(),
                    sportiveManager.getUserInfo().getUid(),
                    fieldId,
                    sportFieldName,
                    fieldImg,
                    price * mSearchFieldConfig.getDuration()
            );
            Timber.d("saveFieldBookingData: %s", fieldBooking);
            saveFieldBookingUseCase.execute(new SaveFieldBookingObserver(), fieldBooking);

        } else {
            mView.showNotLoggedIn();
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
            Timber.e("onError: %s", e.getMessage());
        }


    }


    private class GetAvailableFieldIdListObserver extends DisposableMaybeObserver<List<String>> {
        @Override
        public void onSuccess(List<String> miniFieldIdList) {
            Timber.d("onSuccess: %s", miniFieldIdList);
            getSportFieldUseCase.execute(new GetSportFieldObserver(), miniFieldIdList);
        }

        @Override
        public void onError(Throwable e) {
            Timber.e("onError: %s", e.getMessage());
        }

        @Override
        public void onComplete() {
            Timber.d("onComplete");
        }
    }



    private class GetSportFieldObserver extends DisposableObserver<SportField> {
        @Override
        public void onNext(SportField sportField) {
            Timber.e("onNext: %s", sportField);
            int emptyFieldCount;
            mView.hideLoading();
            if (sportField.getSportFieldAddress().getDistrict().equals(mSearchFieldConfig.getDistrictName())) {

                emptyFieldCount = EmptyFieldCountMap.get(sportField.getFieldId()) == null ? 1
                        : EmptyFieldCountMap.get(sportField.getFieldId()) + 1;


                EmptyFieldCountMap.put(sportField.getFieldId(), emptyFieldCount);
                sportFieldList.remove(sportField);
                sportField.setEmpty(EmptyFieldCountMap.get(sportField.getFieldId()));
                sportFieldList.add(sportField);
                mView.testShow(sportFieldList);
            }
        }

        @Override
        public void onError(Throwable e) {
            Timber.e("onError: %s", e.getMessage());
        }

        @Override
        public void onComplete() {
            Timber.e("onComplete");
        }
    }

}
