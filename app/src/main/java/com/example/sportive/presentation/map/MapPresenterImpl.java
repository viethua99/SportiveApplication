package com.example.sportive.presentation.map;

import com.example.domain.interactor.districtlocation.GetDistrictLocationListUseCase;
import com.example.domain.model.DistrictLocation;
import com.example.domain.model.EmptyParam;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableMaybeObserver;
import timber.log.Timber;

/**
 * Created by Viet Hua on 04/19/2020.
 */
public class MapPresenterImpl implements MapContract.Presenter {

    @Inject
    GetDistrictLocationListUseCase getDistrictLocationListUseCase;

    MapContract.View mView;

    @Inject
    public MapPresenterImpl() {

    }

    @Override
    public void attachView(MapContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        getDistrictLocationListUseCase.dispose();
        mView = null;
    }

    @Override
    public void retrieveDistrictLocationList() {
        getDistrictLocationListUseCase.execute(new GetDistrictLocationListObserver(), new EmptyParam());
    }

    private class GetDistrictLocationListObserver extends DisposableMaybeObserver<List<DistrictLocation>> {
        @Override
        public void onSuccess(List<DistrictLocation> districtLocationList) {
            Timber.d("onSuccess: %s", districtLocationList);
            mView.showMarkerForEachDistrict(districtLocationList);
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
