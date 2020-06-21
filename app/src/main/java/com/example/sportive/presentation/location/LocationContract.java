package com.example.sportive.presentation.location;

import com.example.domain.model.DistrictLocation;
import com.example.sportive.presentation.base.BasePresenter;
import com.example.sportive.presentation.base.BaseView;

import java.util.List;

/**
 * Created by Viet Hua on 4/10/2020
 */
public interface LocationContract {
    interface View extends BaseView {
        void showDistrictLocationList(List<DistrictLocation> districtLocationList);

    }

    interface Presenter extends BasePresenter<View> {
        void getDistrictLocationList();
    }
}
