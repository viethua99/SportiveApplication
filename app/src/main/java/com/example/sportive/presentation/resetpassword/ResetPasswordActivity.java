package com.example.sportive.presentation.resetpassword;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sportive.R;
import com.example.sportive.presentation.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import dagger.android.AndroidInjection;
import timber.log.Timber;

/**
 * Created by Viet Hua on 05/04/2020.
 */
public class ResetPasswordActivity extends BaseActivity implements ResetPasswordContract.View {

    @BindView(R.id.edt_email)
    EditText edtEmail;

    @Inject
    ResetPasswordContract.Presenter presenter;

    public static void startResetPasswordActivity(AppCompatActivity activity) {
        Intent intent = new Intent(activity, ResetPasswordActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected int getResLayoutId() {
        return R.layout.activity_resetpassword;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.d("onCreate");
        AndroidInjection.inject(this);
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
    public void showResetPasswordConfirmMessage() {
        showToastMessage("Vui lòng kiểm tra email để thực hiện reset mật khẩu");
        finish();
    }

    @Override
    public void showResetPasswordErrorMessage() {
        showToastMessage("Email này không tồn tại hoặc đã bị khoá");
    }

    @OnClick(R.id.txt_back)
    public void onBackClick() {
        Timber.d("onBackClick");
        finish();
    }

    @OnClick(R.id.btn_reset_password)
    public void onResetPasswordClick() {
        Timber.d("onResetPasswordClick");
        String email = edtEmail.getText().toString().trim();
        presenter.resetPasswordFromEmail(email);
    }
}
