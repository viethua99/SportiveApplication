package com.example.sportive.presentation.home;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.sportive.R;
import com.example.sportive.presentation.base.BaseFragment;
import com.example.sportive.presentation.location.LocationActivity;
import com.example.sportive.presentation.result.ResultActivity;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.OnClick;
import timber.log.Timber;
import utils.TimeUtils;

/**
 * Created By Viet Hua on 4/7/2020
 */
public class HomeFragment extends BaseFragment {
    public static final String TAG = HomeFragment.class.getSimpleName();
    private final Calendar myCalendar = Calendar.getInstance();
    @BindView(R.id.edt_near_field)
    EditText edtNearField;
    @BindView(R.id.edt_play_hour)
    EditText edtPlayHour;
    @BindView(R.id.edt_play_date)
    EditText edtPlayDate;
    @BindView(R.id.spinner_duration)
    Spinner durationSpinner;

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
        setupDurationSpinner();
    }


    @Override
    protected void onAttachToContext(Context context) {
        mContext = context;
    }


    private void setupDatePickerDialog() {
        Timber.d("setupDatePickerDialog");
        new DatePickerDialog(getContext(), dataPickerDialogListener,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void setupTimePickerDialog() {
        TimePickerDialog timePickerDialog = TimePickerDialog.newInstance(onTimeSetListener,
                myCalendar.HOUR_OF_DAY,
                myCalendar.MINUTE,
                true);
        timePickerDialog.enableMinutes(false);
        timePickerDialog.show(getFragmentManager(), "TimePickerDialog");
    }

    private void setupDurationSpinner() {
        List<String> durationList = new ArrayList<>();
        durationList.add("1 hour");
        durationList.add("2 hour");
        durationList.add("3 hour");
        durationList.add("4 hour");
        durationList.add("5 hour");
        durationList.add("6 hour");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, durationList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        durationSpinner.setOnItemSelectedListener(onItemSelectedListener);
        durationSpinner.setAdapter(arrayAdapter);
    }

    private DatePickerDialog.OnDateSetListener dataPickerDialogListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            String formattedDate = TimeUtils.getDateFormat(i, i1, i2);
            edtPlayDate.setText(formattedDate);
        }
    };


    private AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
            Timber.d("onTimeSet: %d h", hourOfDay);
            String formattedTime = hourOfDay + "h";
            edtPlayHour.setText(formattedTime);
        }
    };

    @OnClick(R.id.btn_search)
    public void onSearchClick() {
        ResultActivity.startResultActivity((AppCompatActivity) getActivity());
    }

    @OnClick(R.id.btn_select)
    public void onSelectClick() {
        ResultActivity.startResultActivity((AppCompatActivity) getActivity());

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

    @OnClick(R.id.edt_play_hour)
    public void onPlayHourClick() {
        Timber.d("onPlayHourClick");
        setupTimePickerDialog();
    }
}
