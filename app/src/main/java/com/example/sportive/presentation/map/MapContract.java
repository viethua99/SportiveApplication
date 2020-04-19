package com.example.sportive.presentation.map;

import com.example.domain.model.DistrictLocation;
import com.example.sportive.presentation.base.BasePresenter;
import com.example.sportive.presentation.base.BaseView;

import java.util.List;

/**
 * Created by Viet Hua on 04/19/2020.
 */
public interface MapContract {

    interface View extends BaseView {
        void showMarkerForEachDistrict(List<DistrictLocation> districtLocationList);
    }

    interface Presenter extends BasePresenter<View> {
        void retrieveDistrictLocationList();
    }
}
