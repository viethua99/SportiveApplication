package com.example.sportive.presentation.detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.sportive.R;
import com.example.sportive.presentation.base.BaseActivity;

import java.util.Objects;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import dagger.android.AndroidInjection;
import timber.log.Timber;

/**
 * Created by Viet Hua on 4/8/2020
 */
public class DetailActivity extends BaseActivity implements DetailContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    DetailContract.Presenter presenter;

    public static void startDetailActivity(AppCompatActivity activity) {
        Timber.d("startDetailActivity");
        Intent intent = new Intent(activity, DetailActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected int getResLayoutId() {
        return R.layout.acitivity_detail;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.d("onCreate");
        AndroidInjection.inject(this);
        setupToolbar();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Timber.d("onStart");
        presenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Timber.d("onDestroy");
        presenter.dropView();
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Timber.d("onOptionsItemSelected: %s ", item.getTitle());
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    private void setupToolbar() {
        Timber.d("setupToolbar");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }
}
