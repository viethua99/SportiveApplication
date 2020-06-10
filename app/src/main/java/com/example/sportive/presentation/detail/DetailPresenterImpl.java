package com.example.sportive.presentation.detail;

import com.example.domain.interactor.fieldbooking.GetFieldBookingListUseCase;
import com.example.domain.interactor.sportfield.GetSportFieldByIdUseCase;
import com.example.domain.model.SportField;

import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.observers.DisposableMaybeObserver;
import timber.log.Timber;

/**
 * Created by Viet Hua on 4/10/2020
 */
public class DetailPresenterImpl implements DetailContract.Presenter {

    @Inject
    GetSportFieldByIdUseCase getSportFieldByIdUseCase;

    DetailContract.View mView;



    @Inject
    public DetailPresenterImpl() {

    }

    @Override
    public void attachView(DetailContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        getSportFieldByIdUseCase.dispose();
        mView = null;
    }

    @Override
    public void getSportFieldDataById(String id) {
        Timber.e("getSportFieldDataById");
        getSportFieldByIdUseCase.execute(new GetSportFieldDataByIdObserver(), id);

    }


    private class GetSportFieldDataByIdObserver extends DisposableMaybeObserver<SportField> {
        @Override
        public void onSuccess(SportField sportField) {
            Timber.d("onSuccess: %s", sportField);
            mView.showSportField(sportField);
            mView.showTotalPrice(sportField.getPrice());
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
