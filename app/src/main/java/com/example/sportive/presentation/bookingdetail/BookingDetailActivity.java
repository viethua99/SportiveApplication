package com.example.sportive.presentation.bookingdetail;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.domain.model.FieldBooking;
import com.example.domain.model.SportField;
import com.example.sportive.R;
import com.example.sportive.presentation.base.BaseActivity;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import dagger.android.AndroidInjection;
import timber.log.Timber;
import utils.SportiveUtils;
import utils.TimeUtils;

/**
 * Created by Viet Hua on 05/06/2020.
 */
public class BookingDetailActivity extends BaseActivity implements BookingDetailContract.View {
    private static final int VIEW_FLIPPER_TIME = 3000;
    private static final String KEY_BOOKING_ID = "KEY_BOOKING_ID";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.txt_play_date)
    TextView tvPlayDate;
    @BindView(R.id.txt_play_time)
    TextView tvPlayTime;
    @BindView(R.id.txt_total_price)
    TextView tvTotalPrice;
    @BindView(R.id.txt_field_address)
    TextView tvFieldAddress;
    @BindView(R.id.txt_duration)
    TextView tvDuration;
    @BindView(R.id.txt_field_name)
    TextView tvFieldName;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.view_flipper)
    ViewFlipper viewFlipper;
    @BindView(R.id.img_field1)
    ImageView imgField1;
    @BindView(R.id.img_field2)
    ImageView imgField2;
    @BindView(R.id.img_field3)
    ImageView imgField3;
    @BindView(R.id.layout_detail)
    RelativeLayout rlDetail;


    @Inject
    BookingDetailContract.Presenter presenter;

    private String bookingId;

    public static void startBookingDetailActivity(AppCompatActivity activity, String bookingId) {
        Timber.d("startBookingDetailActivity");
        Intent intent = new Intent(activity, BookingDetailActivity.class);
        intent.putExtra(KEY_BOOKING_ID, bookingId);
        activity.startActivity(intent);
    }

    @Override
    protected int getResLayoutId() {
        return R.layout.activity_bookingdetail;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.d("onCreate");
        AndroidInjection.inject(this);
        getBundleData();
        setupToolbar();
        setupViewFlipper();
        Timber.d(bookingId);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Timber.d("onStart");
        presenter.attachView(this);
        presenter.getBookingDataById(bookingId);
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
    public void showBookingData(FieldBooking fieldBooking) {
        Timber.d("showBookingData: %s", fieldBooking);
        tvPlayDate.setText(String.format("Ngày đá: %s", TimeUtils.convertMillisecondsToDateFormat(fieldBooking.getStartTime())));
        tvPlayTime.setText(String.format("Giờ chơi: %s - %s",
                TimeUtils.convertMillisecondsToHourFormat(fieldBooking.getStartTime()),
                TimeUtils.convertMillisecondsToHourFormat(fieldBooking.getFinishTime())));
        tvTotalPrice.setText(SportiveUtils.getPriceWithDotAndVietnameseCurrencyFormat(fieldBooking.getTotalPrice()));
        tvDuration.setText(SportiveUtils.getDurationFormat(fieldBooking.getDuration()));

        //Test
        Glide.with(this).load(fieldBooking.getFieldImg()).into(imgField1);
        Glide.with(this).load("https://cdn.pixabay.com/photo/2016/06/15/01/11/soccer-1457988_1280.jpg").into(imgField2);
        Glide.with(this).load("https://cdn.pixabay.com/photo/2017/06/23/23/49/youth-2436343_960_720.jpg").into(imgField3);
    }

    @Override
    public void showFieldData(SportField sportField) {
        Timber.d("showFieldData: %s", sportField);
        progressBar.setVisibility(View.GONE);
        tvFieldAddress.setText(String.format("%s, %s", sportField.getSportFieldAddress().getStreet(), sportField.getSportFieldAddress().getDistrict()));
        tvFieldName.setText(sportField.getName());
        rlDetail.setVisibility(View.VISIBLE);
    }

    private void getBundleData() {
        Timber.d("getBundleData");
        Bundle bundle = getIntent().getExtras();
        bookingId = bundle.getString(KEY_BOOKING_ID);
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
