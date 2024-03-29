package com.example.sportive.presentation.home;

import com.example.domain.model.SportField;
import com.example.sportive.presentation.base.BasePresenter;
import com.example.sportive.presentation.base.BaseView;

/**
 * Created by Viet Hua on 4/10/2020
 */
public interface HomeContract {
    interface View extends BaseView {

    }

    interface Presenter extends BasePresenter<View> {
        String getFormattedDate(int year, int month, int dayOfMonth);

        String getFormattedHour(int hourOfDay);

        long getStartTime();

        long getFinishTime();

        int getDurationTime();

        float getLatitude();

        float getLongitude();

        String getDistrictName();

        void saveDurationTime(int duration);

        void saveDistrictLocation(float latitude, float longitude,String districtName);
    }
}
