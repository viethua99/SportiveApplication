package com.example.sportive.presentation.result;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.domain.interactor.fieldbooking.GetAvailableFieldIdListUseCase;
import com.example.domain.interactor.fieldbooking.SaveFieldBookingUseCase;
import com.example.domain.interactor.sportfield.GetSportFieldUseCase;
import com.example.domain.model.FieldBooking;
import com.example.domain.model.SearchFieldConfig;
import com.example.domain.model.SportField;
import com.example.sportive.di.SportiveManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

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

    Map<SportField, Integer> test = new HashMap<>();
    List<SportField> testList = new ArrayList<>();

    private class GetSportFieldObserver extends DisposableObserver<SportField> {
        @Override
        public void onNext(SportField sportField) {
            Timber.e("onNext: %s", sportField);
            mView.hideLoading();
            if (sportField.getSportFieldAddress().getDistrict().equals(mSearchFieldConfig.getDistrictName())) {


                if (test.get(sportField) == null) {
                    test.put(sportField, 1);
                } else {
                    test.put(sportField, test.get(sportField) + 1);
                }

                if (testList.contains(sportField)) {
                    testList.remove(sportField);
                }
                sportField.setEmpty(test.get(sportField));
                testList.add(sportField);
                Timber.e("TEST2: %s", test.get(sportField));
//                mView.showAvailableSportFieldData(sportField);
                mView.testShow(testList);
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
