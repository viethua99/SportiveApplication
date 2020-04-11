package com.example.sportive.presentation.result;

import com.example.domain.model.SportField;
import com.example.sportive.presentation.base.BasePresenter;
import com.example.sportive.presentation.base.BaseView;

import java.util.List;

/**
 * Created by Viet Hua on 4/10/2020
 */
public interface ResultContract {
    interface View extends BaseView {
        void showSportFieldList(List<SportField> sportFieldList);
    }

    interface Presenter extends BasePresenter<View> {
        void getSportFieldList();
    }
}
