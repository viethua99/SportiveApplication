package com.example.sportive.presentation.home;

import com.example.domain.interactor.sportfield.AddSportFieldUsecase;
import com.example.domain.model.SportField;

import javax.inject.Inject;

import io.reactivex.observers.DisposableCompletableObserver;
import timber.log.Timber;
import utils.TimeUtils;

/**
 * Created by Viet Hua on 4/10/2020
 */
public class HomePresenterImpl implements HomeContract.Presenter {
    HomeContract.View mView;
    private boolean currentDateFlag = false;
    @Inject
    AddSportFieldUsecase addSportFieldUsecase;

    @Inject
    public HomePresenterImpl() {

    }

    @Override
    public void attachView(HomeContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
    }

    @Override
    public String getFormattedDate(int year, int month, int dayOfMonth) {
        long dateInMilliseconds = 0L;
        if (!currentDateFlag) { //Make sure only get current date for the first time
            dateInMilliseconds = TimeUtils.getCurrentDateInMilliseconds();
            currentDateFlag = true;
        }
        if (year != -1 && month != -1 && dayOfMonth != -1) {
            dateInMilliseconds = TimeUtils.getDateFormatInMilliseconds(year, month, dayOfMonth);
        }
        return TimeUtils.convertMillisecondsToDateFormat(dateInMilliseconds);
    }

    @Override
    public void saveSportFieldData(SportField sportField) {
        addSportFieldUsecase.execute(new AddSportFieldDataObserver(), sportField);
    }

    private class AddSportFieldDataObserver extends DisposableCompletableObserver {
        @Override
        public void onComplete() {
            Timber.d("onComplete");
        }

        @Override
        public void onError(Throwable e) {
            Timber.e(e.getMessage());
        }
    }
}
