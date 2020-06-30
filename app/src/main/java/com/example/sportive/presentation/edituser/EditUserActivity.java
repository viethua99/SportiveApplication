package com.example.sportive.presentation.edituser;

import android.content.Intent;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.sportive.R;
import com.example.sportive.presentation.base.BaseActivity;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import dagger.android.AndroidInjection;
import timber.log.Timber;

/**
 * Created by Viet Hua on 06/30/2020.
 */
public class EditUserActivity extends BaseActivity implements EditUserContract.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.img_user_avatar)
    ImageView imgUserAvatar;
    @BindView(R.id.img_user_cover)
    ImageView imgUserCover;

    @Inject
    EditUserContract.Presenter presenter;

    public static void startEditUserActivity(AppCompatActivity activity) {
        Timber.d("startEditUserActivity");
        Intent intent = new Intent(activity, EditUserActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected int getResLayoutId() {
        return R.layout.activity_edituser;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.d("onCreate");
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
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Timber.d("onOptionsItemSelected: %s", item.getItemId());
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;

        }
        return true;
    }

    private void setupViews() {
        Timber.d("setupViews");
        setupToolbar();
        showUserInfo();
    }

    private void setupToolbar() {
        Timber.d("setupToolbar");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void showUserInfo() {
        Timber.d("showUserInfo");
        Glide.with(this)
                .load("https://cdn.pixabay.com/photo/2016/06/15/01/11/soccer-1457988_1280.jpg")
                .apply(RequestOptions.circleCropTransform())
                .into(imgUserAvatar);
        Glide.with(this)
                .load("https://cdn.pixabay.com/photo/2017/06/23/23/49/youth-2436343_960_720.jpg")
                .into(imgUserCover);

        //SET GRAYSCALE FOR ALL PICTURES
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0.3f);
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(colorMatrix);
        imgUserAvatar.setColorFilter(filter);
        imgUserCover.setColorFilter(filter);

    }
}
