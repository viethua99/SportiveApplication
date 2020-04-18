package com.example.sportive.presentation.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.HasAndroidInjector;

/**
 * Created by Viet Hua on 4/7/2020
 */
public abstract class BaseFragment extends Fragment {
    protected Context context;
    private Unbinder unbinder;

    protected abstract int getResLayoutId();

    protected abstract void onMyCreatedView(View view);

    protected abstract void onAttachToContext(Context context);

    @TargetApi(23)
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onAttachToContext(context);
        this.context = context;
    }

    /**
     * deprecated on API 23
     * use onAttachToContext instead
     *
     * @param activity
     */
    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (Build.VERSION.SDK_INT < 23) {
            onAttachToContext(activity);
            this.context = activity;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getResLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        onMyCreatedView(view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
