package com.example.sportive.presentation.profile;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.example.sportive.R;
import com.example.sportive.presentation.base.BaseFragment;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import dagger.android.support.AndroidSupportInjection;
import timber.log.Timber;

/**
 * Created by Viet Hua on 4/7/2020
 */
public class ProfileFragment extends BaseFragment implements ProfileContract.View {
    public static final String TAG = ProfileFragment.class.getSimpleName();
    @Inject
    ProfileContract.Presenter presenter;

    Context mContext;

    public static ProfileFragment getInstance() {
        Timber.d("getInstance");
        ProfileFragment fragment = new ProfileFragment();
        Bundle data = new Bundle();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    protected int getResLayoutId() {
        return R.layout.fragment_profile;
    }

    @Override
    protected void onMyCreatedView(View view) {
        Timber.d("onMyCreatedView");
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

    @Override
    protected void onAttachToContext(Context context) {
        mContext = context;
    }
}
