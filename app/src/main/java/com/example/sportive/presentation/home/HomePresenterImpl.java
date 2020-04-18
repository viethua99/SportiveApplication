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
    private int year = TimeUtils.getCurrentYear();
    private int month = TimeUtils.getCurrentMonth();
    private int dayOfMonth = TimeUtils.getCurrentDayOfMonth();
    private int hourOfDay = TimeUtils.getCurrentHour() + 1;
    private int duration = 1;

    @Inject
    AddSportFieldUsecase addSportFieldUsecase;

    @Inject
    public HomePresenterImpl() {

    }

    @Override
    public void attachView(HomeContract.View view) {
        addSportFieldUsecase.dispose();
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
    }

    @Override
    public String getFormattedDate(int year, int month, int dayOfMonth) {
        if (year != 0 && month != 0 && dayOfMonth != 0) {
            this.year = year;
            this.month = month;
            this.dayOfMonth = dayOfMonth;
        }
        return TimeUtils.getDateFormat(this.year, this.month, this.dayOfMonth);
    }

    @Override
    public void saveSportFieldData(SportField sportField) {
        addSportFieldUsecase.execute(new AddSportFieldDataObserver(), sportField);
    }

    @Override
    public String getFormattedHour(int hourOfDay) {
        if (hourOfDay != 0) {
            this.hourOfDay = hourOfDay;
        }
        return String.valueOf(this.hourOfDay) + "h";
    }

    @Override
    public void saveDurationTime(int duration) {
        this.duration = duration;
    }

    @Override
    public long getStartTime() {
        long startTime = TimeUtils.getDateFormatInMilliseconds(this.year, this.month, this.dayOfMonth, this.hourOfDay);
        return startTime;
    }

    @Override
    public long getFinishTime() {
        int finishHour = this.hourOfDay + duration;
        long finishTime = TimeUtils.getDateFormatInMilliseconds(this.year, this.month, this.dayOfMonth, finishHour);
        return finishTime;
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
