package com.example.sportive.presentation.home;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.sportive.R;
import com.example.sportive.presentation.base.BaseFragment;
import com.example.sportive.presentation.location.LocationActivity;
import com.example.sportive.presentation.result.ResultActivity;

import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.OnClick;
import timber.log.Timber;

/**
 * Created By Viet Hua on 4/7/2020
 */
public class HomeFragment extends BaseFragment {
    public static final String TAG = HomeFragment.class.getSimpleName();
    @BindView(R.id.edt_played_field)
    EditText edtPlayedField;
    @BindView(R.id.edt_near_field)
    EditText edtNearField;

    Context mContext;
    private final Calendar myCalendar = Calendar.getInstance();


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


    private void setupDatePickerDialog() {
        Timber.d("setupDatePickerDialog");
        new DatePickerDialog(getContext(),R.style.DialogTheme, dataPickerDialogListener,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private DatePickerDialog.OnDateSetListener dataPickerDialogListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            Timber.d("onDateSet");
        }
    };

    @OnClick(R.id.btn_search)
    public void onSearchClick() {
        ResultActivity.startResultActivity((AppCompatActivity) getActivity());
    }

    @OnClick(R.id.btn_select)
    public void onSelectClick() {
    }

    @OnClick(R.id.btn_location)
    public void onLocationClick() {
        edtNearField.setText("Sân bóng gần bạn");
    }

    @OnClick(R.id.edt_near_field)
    public void onNearFieldClick() {
        LocationActivity.startLocationActivity((AppCompatActivity) getActivity());

    }

    @OnClick(R.id.edt_play_date)
    public void onPlayDateClick() {
        Timber.d("onNearFieldClick");
        setupDatePickerDialog();
    }
}
