package com.example.sportive.presentation.profile;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.domain.model.UserInfo;
import com.example.sportive.R;
import com.example.sportive.presentation.base.BaseFragment;
import com.example.sportive.presentation.login.LoginActivity;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.OnClick;
import dagger.android.support.AndroidSupportInjection;
import timber.log.Timber;

/**
 * Created by Viet Hua on 4/7/2020
 */
public class ProfileFragment extends BaseFragment implements ProfileContract.View {
    public static final String TAG = ProfileFragment.class.getSimpleName();

    @BindView(R.id.ll_not_login)
    LinearLayout notLoggedInLayout;
    @BindView(R.id.scrollview_profile)
    ScrollView profileScrollView;
    @BindView(R.id.txt_username)
    TextView username;
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
        presenter.checkIfUserIsLoggedIn();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Timber.d("onDestroy");
        presenter.dropView();
    }

    @Override
    public void showNotLoggedInView() {
        Timber.d("showNotLoggedInView");
        notLoggedInLayout.setVisibility(View.VISIBLE);
        profileScrollView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showUserInfo(UserInfo userInfo) {
        Timber.d("showUserInfo");
        notLoggedInLayout.setVisibility(View.GONE);
        profileScrollView.setVisibility(View.VISIBLE);
        username.setText(userInfo.getName());
    }

    @Override
    protected void onAttachToContext(Context context) {
        mContext = context;
    }

    @Override
    public void showSignOutSuccess() {
        showToastMessage("Log out successfully");
        LoginActivity.startLoginActivity((AppCompatActivity) getActivity());
    }

    @OnClick(R.id.txt_logout)
    public void onLogOutClick() {
        Timber.d("onLogoutClick");
        presenter.logout();
    }

    @OnClick(R.id.btn_login)
    public void onLogInClick() {
        LoginActivity.startLoginActivity((AppCompatActivity) getActivity());
    }
}
