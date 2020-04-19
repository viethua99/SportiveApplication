package com.example.sportive.presentation.result;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.domain.model.SearchFieldConfig;
import com.example.domain.model.SportField;
import com.example.sportive.R;
import com.example.sportive.presentation.base.BaseActivity;
import com.example.sportive.presentation.base.ItemClickListener;
import com.example.sportive.presentation.detail.DetailActivity;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import dagger.android.AndroidInjection;
import timber.log.Timber;

/**
 * Created by Viet Hua on 4/7/2020
 */
public class ResultActivity extends BaseActivity implements ResultContract.View {
    public static final String HANDLE_SEARCH_FIELD = "HANDLE_SEARCH_FIELD";
    @BindView(R.id.rv_result)
    RecyclerView resultRecyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    private SearchFieldConfig searchFieldConfig;

    @Inject
    ResultContract.Presenter presenter;

    private ResultRecyclerViewAdapter resultRecyclerViewAdapter;

    public static void startResultActivity(AppCompatActivity activity, SearchFieldConfig searchFieldConfig) {
        Timber.d("startResultActivity");
        Intent intent = new Intent(activity, ResultActivity.class);
        intent.putExtra(HANDLE_SEARCH_FIELD, searchFieldConfig);
        activity.startActivity(intent);
    }

    @Override
    protected int getResLayoutId() {
        return R.layout.activity_result;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.d("onCreate");
        AndroidInjection.inject(this);
        searchFieldConfig = (SearchFieldConfig) getIntent().getSerializableExtra(HANDLE_SEARCH_FIELD);

        presenter.attachView(this);
        presenter.getFieldBookingList(searchFieldConfig);

        setupViews();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Timber.d("onStart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Timber.d("onDestroy");
        presenter.dropView();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Timber.d("onOptionsItemSelect: %s", item.getTitle());
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }


    @Override
    public void showAvailableSportFieldData(SportField sportField) {
        Timber.d("showAvailableSpotFieldData");
        resultRecyclerViewAdapter.addData(sportField);
        progressBar.setVisibility(View.GONE);
    }

    private void setupViews() {
        Timber.d("setupViews");
        setupToolBar();
        setupRecyclerView();
    }

    private void setupToolBar() {
        Timber.d("setupToolBar");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    private void setupRecyclerView() {
        Timber.d("setupRecyclerView");
        resultRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        resultRecyclerViewAdapter = new ResultRecyclerViewAdapter(this, resultItemListener);
        resultRecyclerViewAdapter.setButtonClickListener(detailButtonClickListener, bookingButtonClickListener);
        resultRecyclerViewAdapter.setDuration(searchFieldConfig.getDuration());
        resultRecyclerView.setAdapter(resultRecyclerViewAdapter);
    }


    ItemClickListener resultItemListener = new ItemClickListener() {
        @Override
        public void onClickListener(int position) {
            Timber.d("OnClickListener: %d", position);

        }

        @Override
        public void onLongClickListener(int position) {

        }
    };
    ItemClickListener detailButtonClickListener = new ItemClickListener() {
        @Override
        public void onClickListener(int position) {
            Timber.d("onDetailButtonClick: %d", position);
            String sportFieldId = resultRecyclerViewAdapter.getItem(position).getFieldId();
            DetailActivity.startDetailActivity(ResultActivity.this, sportFieldId, searchFieldConfig.getDuration());

        }

        @Override
        public void onLongClickListener(int position) {

        }
    };

    ItemClickListener bookingButtonClickListener = new ItemClickListener() {
        @Override
        public void onClickListener(int position) {
            Timber.d("onBookingButtonClick: %d", position);
            showToastMessage("Đặt sân thành công");
            finish();

        }

        @Override
        public void onLongClickListener(int position) {

        }
    };

}
