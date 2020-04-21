package com.example.sportive.presentation.map;

import android.location.Location;

import com.example.domain.model.DistrictLocation;
import com.example.domain.model.SportField;
import com.example.sportive.presentation.base.BasePresenter;
import com.example.sportive.presentation.base.BaseView;

import java.util.List;

/**
 * Created by Viet Hua on 04/19/2020.
 */
public interface MapContract {

    interface View extends BaseView {
        void showNearbySportFieldList(List<SportField> sportFieldList);
    }

    interface Presenter extends BasePresenter<View> {
        void getNearbySportFieldList(Location currentLocation);
    }
}
