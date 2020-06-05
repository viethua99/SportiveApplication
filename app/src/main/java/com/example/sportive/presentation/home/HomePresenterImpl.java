package com.example.sportive.presentation.home;

import javax.inject.Inject;

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
    private float latitude;
    private float longitude;
    private String districtName;


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
        if (year != 0 && month != 0 && dayOfMonth != 0) {
            this.year = year;
            this.month = month;
            this.dayOfMonth = dayOfMonth;
        }
        return TimeUtils.getDateFormat(this.year, this.month, this.dayOfMonth);
    }


    @Override
    public String getFormattedHour(int hourOfDay) {
        Timber.d("getFormattedHour: %d",hourOfDay);
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
    public int getDurationTime() {
        return this.duration;
    }

    @Override
    public long getStartTime() {
        long startTime = TimeUtils.getDateFormatInMilliseconds(this.year, this.month, this.dayOfMonth, this.hourOfDay);
        return startTime;
    }

    @Override
    public long getFinishTime() {
        int finishHour = hourOfDay + duration;
        long finishTime = TimeUtils.getDateFormatInMilliseconds(this.year, this.month, this.dayOfMonth, finishHour);
        return finishTime;
    }

    @Override
    public float getLatitude() {
        return this.latitude;
    }

    @Override
    public float getLongitude() {
        return this.longitude;
    }

    @Override
    public String getDistrictName() {
        return this.districtName;
    }

    @Override
    public void saveDistrictLocation(float latitude, float longitude, String districtName) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.districtName = districtName;
    }
}
