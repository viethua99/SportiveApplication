package com.example.sportive.presentation.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.sportive.R;
import com.example.sportive.presentation.base.BaseActivity;
import com.example.sportive.presentation.base.Constants;
import com.example.sportive.presentation.booking.BookingFragment;
import com.example.sportive.presentation.home.HomeFragment;
import com.example.sportive.presentation.profile.ProfileFragment;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import butterknife.BindView;
import dagger.android.AndroidInjection;
import timber.log.Timber;

/**
 * Created By Viet Hua on 4/7/2020
 */
public class MainActivity extends BaseActivity implements MainContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.nav_main)
    BottomNavigationView bottomNavigationView;

    @Inject
    MainContract.Presenter presenter;

    public static void startMainActivity(AppCompatActivity activity) {
        Timber.d("startMainActivity");
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected int getResLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.d("onCreated");
        AndroidInjection.inject(this);
        setupViews();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Timber.d("onStart");
        presenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Timber.d("onDestroy");
        presenter.dropView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Timber.d("onCreateOptionsMenu");
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Timber.d("onOptionsItemSelected: %s", item.getTitle());
        return true;
    }

    @Override
    public void onBackPressed() {
        Timber.d("onBackPressed");
    }

    private void setupViews() {
        Timber.d("setupViews");
        addFragment(HomeFragment.getInstance(), HomeFragment.TAG, R.id.fragment_container);
        setupToolbar();
        setupBottomNavigationView();
    }

    private void setupToolbar() {
        Timber.d("setupToolbar");
        toolbar.inflateMenu(R.menu.main);
        setSupportActionBar(toolbar);

    }

    private void openScreenByTag(String tag) {
        Timber.d("openScreenByTag: %s", tag);
        switch (tag) {
            case Constants.TAG_HOME:
                replaceFragment(HomeFragment.getInstance(), HomeFragment.TAG, R.id.fragment_container);
                break;
            case Constants.TAG_PROFILE:
                replaceFragment(ProfileFragment.getInstance(), ProfileFragment.TAG, R.id.fragment_container);
                break;
            case Constants.TAG_BOOKING:
                replaceFragment(BookingFragment.getInstance(), BookingFragment.TAG, R.id.fragment_container);
                break;
        }
        appBarLayout.setVisibility(tag.equals(Constants.TAG_PROFILE) ? View.GONE : View.VISIBLE);
    }

    private void setupBottomNavigationView() {
        Timber.d("setupBottomNavigationView");
        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavigationViewListener);
    }


    BottomNavigationView.OnNavigationItemSelectedListener bottomNavigationViewListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Timber.d("onBottomNavigationView Selected: %s", menuItem.getTitle());
            switch (menuItem.getItemId()) {
                case R.id.item_home:
                    openScreenByTag(Constants.TAG_HOME);
                    break;
                case R.id.item_profile:
                    openScreenByTag(Constants.TAG_PROFILE);
                    break;
                case R.id.item_booking:
                    openScreenByTag(Constants.TAG_BOOKING);
                    break;
            }
            return true;
        }
    };

}
