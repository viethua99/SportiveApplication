package com.example.sportive.presentation.home;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.example.sportive.R;
import com.example.sportive.presentation.base.BaseFragment;
import com.example.sportive.presentation.result.ResultActivity;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.OnClick;

/**
 * Created By Viet Hua on 4/7/2020
 */
public class HomeFragment extends BaseFragment {
    public static final String TAG = HomeFragment.class.getSimpleName();
    Context mContext;

    public static HomeFragment getInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle data = new Bundle();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    protected int getResLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void onMyCreatedView(View view) {

    }

    @Override
    protected void onAttachToContext(Context context) {
        mContext = context;
    }

    @OnClick(R.id.btn_search)
    public void onSearchClick() {
        ResultActivity.startResultActivity((AppCompatActivity) getActivity());
    }
}
