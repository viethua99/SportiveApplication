package com.example.sportive.presentation.booking;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sportive.R;
import com.example.sportive.presentation.base.BaseFragment;
import com.example.sportive.presentation.login.LoginActivity;

import javax.inject.Inject;

import butterknife.OnClick;
import dagger.android.support.AndroidSupportInjection;
import timber.log.Timber;

/**
 * Created by Viet Hua on 04/27/2020.
 */
public class BookingFragment extends BaseFragment implements BookingContract.View {
    public static final String TAG = BookingFragment.class.getSimpleName();
    @Inject
    BookingContract.Presenter presenter;

    Context mContext;

    public static BookingFragment getInstance() {
        Timber.d("getInstance");
        BookingFragment bookingFragment = new BookingFragment();
        Bundle bundle = new Bundle();
        bookingFragment.setArguments(bundle);
        return bookingFragment;
    }

    @Override
    protected int getResLayoutId() {
        return R.layout.fragment_booking;
    }

    @Override
    protected void onMyCreatedView(View view) {
        Timber.d("onMyCreatedView");
    }

    @Override
    protected void onAttachToContext(Context context) {
        mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.d("onCreate");
        AndroidSupportInjection.inject(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        Timber.d("onStart");
        presenter.attachView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Timber.d("onDestroy");
        presenter.dropView();
    }

    @OnClick(R.id.btn_login)
    public void onLoginClick() {
        LoginActivity.startLoginActivity((AppCompatActivity) getActivity());
    }
}