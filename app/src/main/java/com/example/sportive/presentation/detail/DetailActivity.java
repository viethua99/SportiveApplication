package com.example.sportive.presentation.detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

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
    public static final String KEY_DURATION_TIME = "KEY_DURATION_TIME";
    private static final int VIEW_FLIPPER_TIME = 3000;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.img_field1)
    ImageView imgField1;
    @BindView(R.id.img_field2)
    ImageView imgField2;
    @BindView(R.id.img_field3)
    ImageView imgField3;
    @BindView(R.id.txt_field_name)
    TextView tvFieldName;
    @BindView(R.id.txt_field_address)
    TextView tvFieldAddress;
    @BindView(R.id.txt_field_price)
    TextView tvFieldPrice;
    @BindView(R.id.txt_total_price)
    TextView tvTotalPrice;
    @BindView(R.id.txt_duration)
    TextView tvDuration;
    @BindView(R.id.rating_bar)
    RatingBar ratingBar;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.layout_detail)
    RelativeLayout detailLayout;
    @BindView(R.id.view_flipper)
    ViewFlipper viewFlipper;


    @Inject
    DetailContract.Presenter presenter;

    private String fieldId;
    private int duration;

    public static void startDetailActivity(AppCompatActivity activity, String fieldId, int duration) {
        Timber.d("startDetailActivity");
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra(KEY_FIELD_ID, fieldId);
        intent.putExtra(KEY_DURATION_TIME, duration);
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

        getBundleData();
        setupToolbar();
        setupViewFlipper();
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
        progressBar.setVisibility(View.GONE);
        Glide.with(this).load(sportField.getImgPath()).into(imgField1);
        Glide.with(this).load("https://cdn.pixabay.com/photo/2016/06/15/01/11/soccer-1457988_1280.jpg").into(imgField2);
        Glide.with(this).load("https://cdn.pixabay.com/photo/2017/06/23/23/49/youth-2436343_960_720.jpg").into(imgField3);
        tvFieldName.setText(sportField.getName());
        tvFieldPrice.setText(SportiveUtils.getPricePerHourFormat(sportField.getPrice()));
        tvFieldAddress.setText(sportField.getAddress());
        ratingBar.setRating(sportField.getRating());
        detailLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void showTotalPrice(int pricePerHour) {
        tvTotalPrice.setText(SportiveUtils.getPriceWithDotAndVietnameseCurrencyFormat(pricePerHour * duration));
        tvDuration.setText(SportiveUtils.getDurationFormat(duration));
    }

    private void getBundleData() {
        Bundle bundle = getIntent().getExtras();
        fieldId = bundle.getString(KEY_FIELD_ID);
        duration = bundle.getInt(KEY_DURATION_TIME);
    }

    private void setupToolbar() {
        Timber.d("setupToolbar");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    private void setupViewFlipper() {
        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(VIEW_FLIPPER_TIME);
        viewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);
    }
}
