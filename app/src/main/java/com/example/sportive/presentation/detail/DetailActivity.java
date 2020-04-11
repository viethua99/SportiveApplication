package com.example.sportive.presentation.detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.domain.model.SportField;
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
import utils.SportiveUtils;

/**
 * Created by Viet Hua on 4/8/2020
 */
public class DetailActivity extends BaseActivity implements DetailContract.View {
    public static final String KEY_FIELD_ID = "KEY_FIELD_ID";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.img_field)
    ImageView imgField;
    @BindView(R.id.txt_field_name)
    TextView tvFieldName;
    @BindView(R.id.txt_field_address)
    TextView tvFieldAddress;
    @BindView(R.id.txt_field_price)
    TextView tvFieldPrice;
    @BindView(R.id.rating_bar)
    RatingBar ratingBar;


    @Inject
    DetailContract.Presenter presenter;

    private String fieldId;

    public static void startDetailActivity(AppCompatActivity activity, String fieldId) {
        Timber.d("startDetailActivity");
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra(KEY_FIELD_ID, fieldId);
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

        Bundle bundle = getIntent().getExtras();
        fieldId = bundle.getString(KEY_FIELD_ID);
        setupToolbar();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Timber.d("onStart");
        presenter.attachView(this);
        presenter.getSportFieldDataById(fieldId);
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

    @Override
    public void showSportField(SportField sportField) {
        Timber.d("showSportField: %s", sportField);
        Glide.with(this).load(sportField.getImgPath()).into(imgField);
        tvFieldName.setText(sportField.getName());
        tvFieldPrice.setText(SportiveUtils.getPricePerHourFormat(sportField.getPrice()));
        tvFieldAddress.setText(sportField.getAddress());
        ratingBar.setRating(sportField.getRating());
    }

    private void setupToolbar() {
        Timber.d("setupToolbar");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }
}
