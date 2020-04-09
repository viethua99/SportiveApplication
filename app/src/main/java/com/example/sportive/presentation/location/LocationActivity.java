package com.example.sportive.presentation.location;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.domain.model.TestModel;
import com.example.sportive.R;
import com.example.sportive.presentation.base.BaseActivity;
import com.example.sportive.presentation.base.ItemClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import timber.log.Timber;

/**
 * Created by Viet Hua on 4/8/2020
 */
public class LocationActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_location)
    RecyclerView recyclerView;

    LocationRecyclerViewAdapter locationRecyclerViewAdapter;

    public static void startLocationActivity(AppCompatActivity activity) {
        Intent intent = new Intent(activity, LocationActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.d("onCreate");
        setupViews();
    }

    @Override
    protected int getResLayoutId() {
        return R.layout.activity_location;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    private void setupViews() {
        setupToolbar();
        setupRecyclerView();
    }

    private void setupToolbar() {
        toolbar.inflateMenu(R.menu.search);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    private void setupRecyclerView() {
        locationRecyclerViewAdapter = new LocationRecyclerViewAdapter(this, listener);
        locationRecyclerViewAdapter.setData(dataTest());
        recyclerView.setAdapter(locationRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    private List<TestModel> dataTest() {
        List<TestModel> testModelList = new ArrayList<>();
        testModelList.add(new TestModel("Quận 1", "TP.Hồ Chí Minh", "22 sân bóng"));
        testModelList.add(new TestModel("Quận 3", "TP.Hồ Chí Minh", "25 sân bóng"));
        testModelList.add(new TestModel("Quận 5", "TP.Hồ Chí Minh", "34 sân bóng"));
        testModelList.add(new TestModel("Quận 7", "TP.Hồ Chí Minh", "16 sân bóng"));
        testModelList.add(new TestModel("Quận 9", "TP.Hồ Chí Minh", "12 sân bóng"));
        testModelList.add(new TestModel("Quận 12", "TP.Hồ Chí Minh", "40 sân bóng"));
        testModelList.add(new TestModel("Quận Gò Vấp", "TP.Hồ Chí Minh", "12 sân bóng"));
        testModelList.add(new TestModel("Quận Bình Thạnh", "TP.Hồ Chí Minh", "10 sân bóng"));
        testModelList.add(new TestModel("Quận Tân Bình", "TP.Hồ Chí Minh", "43 sân bóng"));
        testModelList.add(new TestModel("Quận Tân Phú", "TP.Hồ Chí Minh", "5 sân bóng"));
        return testModelList;
    }

    ItemClickListener<TestModel> listener = new ItemClickListener<TestModel>() {
        @Override
        public void onClickListener(int position, TestModel testModel) {
            finish();
        }

        @Override
        public void onLongClickListener(int position, TestModel testModel) {

        }
    };
}
