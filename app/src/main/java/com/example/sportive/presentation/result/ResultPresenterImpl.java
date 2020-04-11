package com.example.sportive.presentation.result;

import com.example.domain.interactor.sportfield.GetSportFieldListUseCase;
import com.example.domain.model.EmptyParam;
import com.example.domain.model.SportField;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableMaybeObserver;
import timber.log.Timber;

/**
 * Created by Viet Hua on 4/10/2020
 */
public class ResultPresenterImpl implements ResultContract.Presenter {

    ResultContract.View mView;

    @Inject
    GetSportFieldListUseCase getSportFieldListUseCase;

    @Inject
    ResultPresenterImpl() {

    }

    @Override
    public void attachView(ResultContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
    }

    @Override
    public void getSportFieldList() {
        getSportFieldListUseCase.execute(new GetSportFieldListObserver(), new EmptyParam());

    }

    private class GetSportFieldListObserver extends DisposableMaybeObserver<List<SportField>> {
        @Override
        public void onSuccess(List<SportField> sportFieldList) {
            Timber.d("onSuccess: %s", sportFieldList.toString());
            mView.showSportFieldList(sportFieldList);
        }

        @Override
        public void onError(Throwable e) {
            Timber.e(e.getMessage());
        }

        @Override
        public void onComplete() {
            Timber.d("onComplete");
        }
    }
}
