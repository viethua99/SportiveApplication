package com.example.sportive.presentation.location;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.domain.model.DistrictLocation;
import com.example.domain.model.TestModel;
import com.example.sportive.R;
import com.example.sportive.presentation.base.BaseActivity;
import com.example.sportive.presentation.base.ItemClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import dagger.android.AndroidInjection;
import timber.log.Timber;

/**
 * Created by Viet Hua on 4/8/2020
 */
public class LocationActivity extends BaseActivity implements LocationContract.View {
    public static final int DISTRICT_LOCATION_REQUEST_CODE = 100;
    public static final String KEY_DISTRICT_LOCATION = "KEY_DISTRICT_LOCATION";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_location)
    RecyclerView recyclerView;

    @Inject
    LocationContract.Presenter presenter;

    private LocationRecyclerViewAdapter locationRecyclerViewAdapter;

    public static void startLocationActivityForResult(Fragment fragment) {
        Timber.d("startLocationActivityForResult");
        Intent intent = new Intent(fragment.getActivity(), LocationActivity.class);
        fragment.startActivityForResult(intent, DISTRICT_LOCATION_REQUEST_CODE);
    }

    @Override
    protected int getResLayoutId() {
        return R.layout.activity_location;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.d("onCreate");
        AndroidInjection.inject(this);
        setupViews();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Timber.d("onStart");
        presenter.attachView(this);
        presenter.getDistrictLocationList();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Timber.d("onDestroy");
        presenter.dropView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Timber.d("onCreateOptionsMenu");
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Timber.d("onOptionsItemSelected: %s", item.getTitle());
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    @Override
    public void showDistrictLocationList(List<DistrictLocation> districtLocationList) {
        Timber.d("showDistrictLocationList: %s", districtLocationList);
        locationRecyclerViewAdapter.setData(districtLocationList);
    }

    private void setupViews() {
        Timber.d("setupViews");
        setupToolbar();
        setupRecyclerView();
    }

    private void setupToolbar() {
        Timber.d("setupToolBar");
        toolbar.inflateMenu(R.menu.search);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    private void setupRecyclerView() {
        Timber.d("setupRecyclerView");
        locationRecyclerViewAdapter = new LocationRecyclerViewAdapter(this, listener);
        recyclerView.setAdapter(locationRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    ItemClickListener listener = new ItemClickListener() {
        @Override
        public void onClickListener(int position) {
            Timber.d("onClickListener: %d", position);
            Intent intent = new Intent();
            DistrictLocation districtLocation = locationRecyclerViewAdapter.getItem(position);
            intent.putExtra(KEY_DISTRICT_LOCATION, districtLocation);
            setResult(RESULT_OK, intent);
            finish();

        }

        @Override
        public void onLongClickListener(int position) {

        }
    };
}
