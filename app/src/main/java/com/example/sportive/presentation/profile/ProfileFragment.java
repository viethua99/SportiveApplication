package com.example.sportive.presentation.profile;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.example.sportive.R;
import com.example.sportive.presentation.base.BaseFragment;

/**
 * Created by Viet Hua on 4/7/2020
 */
public class ProfileFragment extends BaseFragment {
    public static final String TAG = ProfileFragment.class.getSimpleName();
    Context mContext;

    public static ProfileFragment getInstance() {
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

    }

    @Override
    protected void onAttachToContext(Context context) {
        mContext = context;
    }
}
