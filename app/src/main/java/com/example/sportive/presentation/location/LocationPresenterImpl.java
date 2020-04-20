package com.example.sportive.presentation.location;

import com.example.domain.interactor.districtlocation.GetDistrictLocationListUseCase;
import com.example.domain.model.DistrictLocation;
import com.example.domain.model.EmptyParam;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableMaybeObserver;
import timber.log.Timber;

/**
 * Created by Viet Hua on 4/10/2020
 */
public class LocationPresenterImpl implements LocationContract.Presenter {
    LocationContract.View mView;


    @Inject
    GetDistrictLocationListUseCase getDistrictLocationListUseCase;

    @Inject
    public LocationPresenterImpl() {

    }

    @Override
    public void attachView(LocationContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        getDistrictLocationListUseCase.dispose();
        mView = null;
    }

    @Override
    public void getDistrictLocationList() {
        getDistrictLocationListUseCase.execute(new GetDistrictLocationListObserver(), new EmptyParam());
    }

    private class GetDistrictLocationListObserver extends DisposableMaybeObserver<List<DistrictLocation>> {
        @Override
        public void onSuccess(List<DistrictLocation> districtLocations) {
            Timber.d("onSuccess: %s", districtLocations.toString());
            mView.showDistrictLocationList(districtLocations);
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
