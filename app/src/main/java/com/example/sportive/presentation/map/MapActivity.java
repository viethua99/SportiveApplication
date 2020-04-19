package com.example.sportive.presentation.map;

import android.content.Intent;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.domain.model.DistrictLocation;
import com.example.sportive.R;
import com.example.sportive.presentation.base.BaseActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import timber.log.Timber;

/**
 * Created by Viet Hua on 04/19/2020.
 */
public class MapActivity extends BaseActivity implements MapContract.View, OnMapReadyCallback {


    @Inject
    MapContract.Presenter presenter;

    private GoogleMap mGoogleMap;

    public static void startMapActivity(AppCompatActivity activity) {
        Timber.d("startMapActivity");
        Intent intent = new Intent(activity, MapActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected int getResLayoutId() {
        return R.layout.activity_map;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.d("onCreate");
        AndroidInjection.inject(this);
        setupMap();

    }

    @Override
    protected void onStart() {
        super.onStart();
        Timber.d("onStart");
        presenter.attachView(this);
        presenter.retrieveDistrictLocationList();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Timber.d("onDestroy");
        presenter.dropView();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Timber.d("onMapReady");
        this.mGoogleMap = googleMap;
    }


    @Override
    public void showMarkerForEachDistrict(List<DistrictLocation> districtLocationList) {
        Timber.d("showMarkerForEachDistrict: %s", districtLocationList);
        for (DistrictLocation districtLocation : districtLocationList) {
            LatLng latLng = new LatLng(districtLocation.getLatitude(), districtLocation.getLongitude());
            mGoogleMap.addMarker(new MarkerOptions().position(latLng).title(districtLocation.getName()));
        }
        LatLng hoChiMinhCity = new LatLng(10.7745397, 106.69918361);
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(hoChiMinhCity));
        mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(12.8f));  //Between 2.0 and 21.0

    }

    private void setupMap() {
        Timber.d("setupMap");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
}
