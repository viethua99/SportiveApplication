package com.example.sportive.presentation.result;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

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
    @BindView(R.id.rv_result)
    RecyclerView resultRecyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @Inject
    ResultContract.Presenter presenter;

    private ResultRecyclerViewAdapter resultRecyclerViewAdapter;

    public static void startResultActivity(AppCompatActivity activity) {
        Timber.d("startResultActivity");
        Intent intent = new Intent(activity, ResultActivity.class);
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
        setupViews();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Timber.d("onStart");
        presenter.attachView(this);
        presenter.getSportFieldList();
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
    public void showSportFieldList(List<SportField> sportFieldList) {
        Timber.d("showSportFieldList: %s ", sportFieldList);
        progressBar.setVisibility(View.GONE);
        resultRecyclerViewAdapter.setData(sportFieldList);
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
            DetailActivity.startDetailActivity(ResultActivity.this, sportFieldId);

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
