package com.example.sportive.presentation.result;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.domain.model.SportField;
import com.example.sportive.R;
import com.example.sportive.presentation.base.BaseActivity;
import com.example.sportive.presentation.base.ItemClickListener;
import com.example.sportive.presentation.detail.DetailActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import timber.log.Timber;

/**
 * Created by Viet Hua on 4/7/2020
 */
public class ResultActivity extends BaseActivity {
    @BindView(R.id.rv_result)
    RecyclerView resultRecyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private ResultRecyclerViewAdapter resultRecyclerViewAdapter;

    public static void startResultActivity(AppCompatActivity activity) {
        Intent intent = new Intent(activity, ResultActivity.class);
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
        return R.layout.activity_result;
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
        setupToolBar();
        setupRecyclerView();
    }

    private void setupToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupRecyclerView() {
        resultRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        resultRecyclerViewAdapter = new ResultRecyclerViewAdapter(this, resultItemListener);
        resultRecyclerViewAdapter.setData(testData());
        resultRecyclerView.setAdapter(resultRecyclerViewAdapter);
    }


    ItemClickListener<SportField> resultItemListener = new ItemClickListener<SportField>() {
        @Override
        public void onClickListener(int position, SportField sportField) {
            DetailActivity.startDetailActivity(ResultActivity.this);
        }

        @Override
        public void onLongClickListener(int position, SportField sportField) {

        }
    };

    private List<SportField> testData() {
        List<SportField> sportFieldList = new ArrayList<>();
        sportFieldList.add(new SportField("https://cdn.pixabay.com/photo/2018/04/23/18/06/ball-3345070_960_720.jpg", "Sân banh Hòa Bình", "phường Tân Chánh Hiệp, quận 12", "6 giờ - 24 giò", "120.000 VND/giờ"));
        sportFieldList.add(new SportField("https://cdn.pixabay.com/photo/2018/04/23/18/06/ball-3345070_960_720.jpg", "Sân banh Hòa Bình", "phường Tân Chánh Hiệp, quận 12", "6 giờ - 24 giò", "120.000 VND/giờ"));
        sportFieldList.add(new SportField("https://cdn.pixabay.com/photo/2018/04/23/18/06/ball-3345070_960_720.jpg", "Sân banh Hòa Bình", "phường Tân Chánh Hiệp, quận 12", "6 giờ - 24 giò", "120.000 VND/giờ"));
        sportFieldList.add(new SportField("https://cdn.pixabay.com/photo/2018/04/23/18/06/ball-3345070_960_720.jpg", "Sân banh Hòa Bình", "phường Tân Chánh Hiệp, quận 12", "6 giờ - 24 giò", "120.000 VND/giờ"));
        sportFieldList.add(new SportField("https://cdn.pixabay.com/photo/2018/04/23/18/06/ball-3345070_960_720.jpg", "Sân banh Hòa Bình", "phường Tân Chánh Hiệp, quận 12", "6 giờ - 24 giò", "120.000 VND/giờ"));
        sportFieldList.add(new SportField("https://cdn.pixabay.com/photo/2018/04/23/18/06/ball-3345070_960_720.jpg", "Sân banh Hòa Bình", "phường Tân Chánh Hiệp, quận 12", "6 giờ - 24 giò", "120.000 VND/giờ"));
        sportFieldList.add(new SportField("https://cdn.pixabay.com/photo/2018/04/23/18/06/ball-3345070_960_720.jpg", "Sân banh Hòa Bình", "phường Tân Chánh Hiệp, quận 12", "6 giờ - 24 giò", "120.000 VND/giờ"));
        sportFieldList.add(new SportField("https://cdn.pixabay.com/photo/2018/04/23/18/06/ball-3345070_960_720.jpg", "Sân banh Hòa Bình", "phường Tân Chánh Hiệp, quận 12", "6 giờ - 24 giò", "120.000 VND/giờ"));
        return sportFieldList;
    }

}
