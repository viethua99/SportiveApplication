package com.example.sportive.presentation.detail;

import com.example.domain.model.SportField;
import com.example.sportive.presentation.base.BasePresenter;
import com.example.sportive.presentation.base.BaseView;

/**
 * Created by Viet Hua on 4/10/2020
 */
public interface DetailContract {
    interface View extends BaseView {
        void showSportField(SportField sportField);

    }

    interface Presenter extends BasePresenter<View> {
        void getSportFieldDataById(String id);
    }
}
