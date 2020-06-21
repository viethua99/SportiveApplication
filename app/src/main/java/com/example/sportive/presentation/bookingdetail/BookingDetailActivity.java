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
import com.example.domain.model.BookingDetail;
import com.example.sportive.R;
import com.example.sportive.presentation.base.BaseActivity;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
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
        setupToolbar();
        setupViewFlipper();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Timber.d("onStart");
        presenter.attachView(this);
        presenter.retrieveBookingDataById(getBookingIdFromBundle());
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
    public void showBookingDetail(BookingDetail bookingDetail) {
        Timber.d("showBookingDetail: %s", bookingDetail);
        progressBar.setVisibility(View.GONE);

        tvPlayDate.setText(String.format("Ngày đá: %s", TimeUtils.convertMillisecondsToDateFormat(bookingDetail.getStartTime())));
        tvPlayTime.setText(String.format("Giờ chơi: %s - %s",
                TimeUtils.convertMillisecondsToHourFormat(bookingDetail.getStartTime()),
                TimeUtils.convertMillisecondsToHourFormat(bookingDetail.getFinishTime())));
        tvTotalPrice.setText(SportiveUtils.getPriceWithDotAndVietnameseCurrencyFormat(bookingDetail.getTotalPrice()));
        tvDuration.setText(SportiveUtils.getDurationFormat(bookingDetail.getDuration()));
        tvFieldAddress.setText(String.format("%s, %s", bookingDetail.getSportFieldAddress().getStreet(),
                bookingDetail.getSportFieldAddress().getDistrict()));
        tvFieldName.setText(bookingDetail.getFieldName());
        Glide.with(this).load(bookingDetail.getFieldImagePath()).into(imgField1);

        //Test
        Glide.with(this).load("https://cdn.pixabay.com/photo/2016/06/15/01/11/soccer-1457988_1280.jpg").into(imgField2);
        Glide.with(this).load("https://cdn.pixabay.com/photo/2017/06/23/23/49/youth-2436343_960_720.jpg").into(imgField3);
        rlDetail.setVisibility(View.VISIBLE);
    }


    @Override
    public void showDeleteBookingSuccess() {
        Timber.d("showDeleteBookingSuccess");
        showToastMessage("Huỷ thành công");
    }

    private String getBookingIdFromBundle() {
        Timber.d("getBundleData");
        Bundle bundle = getIntent().getExtras();
        return bundle.getString(KEY_BOOKING_ID);
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

    private void startShareIntent() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Sportive Application");
        String shareMessage = String.format("Tên sân: %s\nThời gian: %s , %s\nTổng tiền: %s", presenter.getBookingDetail().getFieldName(),
                TimeUtils.convertMillisecondsToDateFormat(presenter.getBookingDetail().getStartTime()),
                TimeUtils.convertMillisecondsToHourFormat(presenter.getBookingDetail().getStartTime()) + "-" +
                        TimeUtils.convertMillisecondsToHourFormat(presenter.getBookingDetail().getFinishTime()),
                SportiveUtils.getPriceWithDotAndVietnameseCurrencyFormat(presenter.getBookingDetail().getTotalPrice()));
        intent.putExtra(Intent.EXTRA_TEXT, shareMessage);
        startActivity(intent);
    }

    @OnClick(R.id.btn_field_canceling)
    public void onFieldCancelingClick() {
        presenter.deleteBookingById(getBookingIdFromBundle());
        finish();
    }

    @OnClick(R.id.btn_share)
    public void onShareClick() {
        startShareIntent();
        finish();
    }

}
