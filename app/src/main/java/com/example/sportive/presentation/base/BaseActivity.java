package com.example.sportive.presentation.base;

import android.os.Bundle;
import android.widget.Toast;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;
import utils.AndroidDialogUtils;

/**
 * Created By Viet Hua on 4/7/2020
 */
public abstract class BaseActivity extends AppCompatActivity implements HasAndroidInjector {
    protected FragmentManager fragmentManager;
    Unbinder unbinder;

    @Inject
    DispatchingAndroidInjector<Object> dispatchingAndroidInjector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getResLayoutId());
        getApplicationContext();
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        unbinder = ButterKnife.bind(this);
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return dispatchingAndroidInjector;
    }

    protected abstract int getResLayoutId();


    public void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void showAlertDialog(String title, String message) {
        AndroidDialogUtils.getInstance().showAlertDialog(this, title, message);
    }

    /**
     * ADD / REPLACE FRAGMENT
     */

    protected void generateFragmentManager() {
        if (fragmentManager == null) {
            fragmentManager = getSupportFragmentManager();
        }
    }

    public void addFragment(Fragment fragment, String tag, int containerId) {
        generateFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(containerId, fragment, tag)
                .addToBackStack(tag)
                .commit();
    }

    public void replaceFragment(Fragment fragment, String tag, int containerId) {
        generateFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(containerId, fragment, tag)
                .addToBackStack(tag)
                .commit();
    }
}
