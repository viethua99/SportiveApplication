package com.example.sportive.presentation.map;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.example.domain.model.SportField;
import com.example.sportive.R;
import com.example.sportive.presentation.base.BaseActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.maps.android.ui.IconGenerator;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import timber.log.Timber;
import utils.SportiveUtils;

/**
 * Created by Viet Hua on 04/19/2020.
 */
public class MapActivity extends BaseActivity implements MapContract.View,
        OnMapReadyCallback,
        GoogleMap.OnCameraMoveStartedListener,
        GoogleMap.OnCameraMoveListener,
        GoogleMap.OnCameraIdleListener {

    private static final int LOCATION_PERMISSION_ID = 40;
    @Inject
    MapContract.Presenter presenter;
    private GoogleMap mGoogleMap;
    List<Marker> SportFieldMarkerList = new ArrayList<>();
    private FusedLocationProviderClient fusedLocationProviderClient;
    private Location currentLocation;


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
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        setupMap();

    }

    @Override
    protected void onStart() {
        super.onStart();
        Timber.d("onStart");
        presenter.attachView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Timber.d("onResume");
        if (isPermissionsGranted()) {
            getLastLocation();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Timber.d("onDestroy");
        presenter.dropView();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Timber.d("onRequestPermissionsResult");
        if (requestCode == LOCATION_PERMISSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            } else {
                showToastMessage("Location Permission Denied");
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Timber.d("onMapReady");
        this.mGoogleMap = googleMap;
        mGoogleMap.setOnCameraMoveStartedListener(this);
        mGoogleMap.setOnCameraMoveListener(this);
        mGoogleMap.setOnCameraIdleListener(this);
        getLastLocation();
    }

    @Override
    public void onCameraMoveStarted(int i) {
        if (i == GoogleMap.OnCameraMoveStartedListener.REASON_GESTURE) {
            Timber.d("onCameraMoveStarted: GESTURE");
            if (!SportFieldMarkerList.isEmpty()) {
                Timber.d("Clear old marker");
                for (Marker marker : SportFieldMarkerList) {
                    marker.remove();
                }
                SportFieldMarkerList.clear();
            }
        } else if (i == GoogleMap.OnCameraMoveStartedListener
                .REASON_API_ANIMATION) {
            Timber.d("onCameraMoveStarted: TYPE");
        } else if (i == GoogleMap.OnCameraMoveStartedListener
                .REASON_DEVELOPER_ANIMATION) {
            Timber.d("onCameraMoveStarted: MOVED");

        }

    }

    @Override
    public void onCameraMove() {
        Timber.d("onCameraMove");
    }

    @Override
    public void onCameraIdle() {
        Timber.d("onCameraIdle");
        LatLng center = mGoogleMap.getCameraPosition().target;
            Location centerLocation = new Location("center");
            centerLocation.setLatitude(center.latitude);
            centerLocation.setLongitude(center.longitude);
            presenter.getNearbySportFieldList(centerLocation);

    }



    @Override
    public void showNearbySportFieldList(List<SportField> sportFieldList) {
        Timber.d("showNearbySportFieldList: %s", sportFieldList);
        IconGenerator iconGenerator = new IconGenerator(this);
        for (SportField sportField : sportFieldList) {
            LatLng latLng = new LatLng(sportField.getLatitude(), sportField.getLongitude());
            Marker marker = mGoogleMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title(sportField.getName())
                    .icon(BitmapDescriptorFactory
                            .fromBitmap(iconGenerator
                            .makeIcon(String.format("%s\n%s", sportField.getName(),
                                    SportiveUtils.getPricePerHourFormat(sportField.getPrice()))))));
            SportFieldMarkerList.add(marker);
        }
    }

    @SuppressLint("MissingPermission")
    private void getLastLocation() {
        Timber.d("getLastLocation");
        if (isPermissionsGranted()) {
            if (isLocationEnabled()) {
                fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        Location location = task.getResult();
                        if (location == null) {
                            //In some devices , if you turn off location and turn on again , previous recorded location will be null
                            //or may the user never turned on location before using this app , so previous location will also be null
                            requestNewLocationData();

                        } else {
                            currentLocation = location;
                            addMarkerAtCurrentLocation(location);
                        }
                    }
                });
            } else {
                showToastMessage("Make sure you turn on location");
            }
        } else {
            requestPermissions();
        }
    }

    @SuppressLint("MissingPermission")
    private void requestNewLocationData() {
        Timber.d("requestNewLocationData");
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);  //Update location every 5-10 seconds
        locationRequest.setNumUpdates(1);  //Update runtime
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fusedLocationProviderClient.requestLocationUpdates(locationRequest,
                new LocationCallback() {
                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        super.onLocationResult(locationResult);
                        Location lastLocation = locationResult.getLastLocation();
                        addMarkerAtCurrentLocation(lastLocation);
                    }
                },
                Looper.myLooper());
    }

    private void setupMap() {
        Timber.d("setupMap");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private boolean isPermissionsGranted() {
        Timber.d("isPermissionGranted");
        //Devices with API level <= 22 , permissions will be granted automatically
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

    private void requestPermissions() {
        Timber.d("requestPermissions");
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                LOCATION_PERMISSION_ID);
    }

    private boolean isLocationEnabled() {
        Timber.d("isLocationEnabled");
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    private void addMarkerAtCurrentLocation(Location currentLocation) {
        Timber.d("addMarkerAtLocation");
        LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        mGoogleMap.addMarker(new MarkerOptions().position(latLng).title("Your Location"));
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(12.8f));  //Interval between 2.0 and 21.0
    }


}
