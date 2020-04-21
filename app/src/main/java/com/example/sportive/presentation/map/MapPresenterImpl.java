package com.example.sportive.presentation.map;

import android.location.Location;

import com.example.domain.interactor.districtlocation.GetDistrictLocationListUseCase;
import com.example.domain.interactor.sportfield.GetSportFieldListUseCase;
import com.example.domain.model.DistrictLocation;
import com.example.domain.model.EmptyParam;
import com.example.domain.model.SportField;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
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

    @Inject
    GetSportFieldListUseCase getSportFieldListUseCase;
    MapContract.View mView;
    private Location currentLocation;

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
    public void getNearbySportFieldList(Location currentLocation) {
        Timber.d("getNearbySportListField: %s", currentLocation);
        this.currentLocation = currentLocation;
        getSportFieldListUseCase.execute(new GetSportFieldListObserver(), new EmptyParam());
    }

    private boolean checkIfSportFieldIsNearby(SportField sportField) {
        Timber.d("checkIfSportFieldIsNearby");
        Location target = new Location("target");
        target.setLatitude(sportField.getLatitude());
        target.setLongitude(sportField.getLongitude());
        if (currentLocation.distanceTo(target) < 6000) {
            return true;
        }
        return false;
    }

    private class GetSportFieldListObserver extends DisposableMaybeObserver<List<SportField>> {
        @Override
        public void onSuccess(List<SportField> sportFields) {
            Timber.d("onSuccess: %s", sportFields);
            List<SportField> nearbySportFieldList = new ArrayList<>();
            for (SportField sportField : sportFields) {
                if (checkIfSportFieldIsNearby(sportField)) {
                    nearbySportFieldList.add(sportField);
                }
            }
            mView.showNearbySportFieldList(nearbySportFieldList);
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
