package com.example.sportive.presentation.home;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.domain.model.SportField;
import com.example.sportive.R;
import com.example.sportive.presentation.base.BaseFragment;
import com.example.sportive.presentation.location.LocationActivity;
import com.example.sportive.presentation.result.ResultActivity;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.OnClick;
import dagger.android.AndroidInjection;
import dagger.android.support.AndroidSupportInjection;
import timber.log.Timber;
import utils.TimeUtils;

/**
 * Created By Viet Hua on 4/7/2020
 */
public class HomeFragment extends BaseFragment implements HomeContract.View {
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

    @Inject
    HomeContract.Presenter presenter;

    Context mContext;


    public static HomeFragment getInstance() {
        Timber.d("getInstance");
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
        Timber.d("onMyCreatedView");
        setupDurationSpinner();
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
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Timber.d("onDestroy");
        presenter.dropView();
    }

    @Override
    protected void onAttachToContext(Context context) {
        mContext = context;
    }


    private void setupDatePickerDialog() {
        Timber.d("setupDatePickerDialog");
        setFixLanguageToVietnamese();
        new DatePickerDialog(Objects.requireNonNull(getContext()), R.style.DialogTheme, dataPickerDialogListener,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void setFixLanguageToVietnamese() {
        Timber.d("setFixLanguageToVietnamese");
        Locale locale = new Locale("vi", "VN");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getContext().getResources().updateConfiguration(config, null);
    }

    private void setupTimePickerDialog() {
        Timber.d("setupTimePickerDialog");
        TimePickerDialog timePickerDialog = TimePickerDialog.newInstance(onTimeSetListener,
                myCalendar.HOUR_OF_DAY,
                myCalendar.MINUTE,
                true);
        timePickerDialog.enableMinutes(false);
        timePickerDialog.show(Objects.requireNonNull(getFragmentManager()), "TimePickerDialog");
    }

    private void setupDurationSpinner() {
        Timber.d("setupDurationSpinner");
        List<String> durationList = new ArrayList<>();
        durationList.add("1 hour");
        durationList.add("2 hour");
        durationList.add("3 hour");
        durationList.add("4 hour");
        durationList.add("5 hour");
        durationList.add("6 hour");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), android.R.layout.simple_spinner_item, durationList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        durationSpinner.setOnItemSelectedListener(onItemSelectedListener);
        durationSpinner.setAdapter(arrayAdapter);
    }

    @OnClick(R.id.btn_search)
    public void onSearchClick() {
        Timber.d("onSearchClick");
        ResultActivity.startResultActivity((AppCompatActivity) getActivity());
    }

    @OnClick(R.id.btn_select)
    public void onSelectClick() {
        Timber.d("onSelectClick");
        ResultActivity.startResultActivity((AppCompatActivity) getActivity());

    }

    @OnClick(R.id.btn_location)
    public void onLocationClick() {
        Timber.d("onLocationClick");
        edtNearField.setText(getString(R.string.field_near_you));
    }

    @OnClick(R.id.edt_near_field)
    public void onNearFieldClick() {
        Timber.d("onNearFieldClick");
        LocationActivity.startLocationActivity((AppCompatActivity) getActivity());

    }

    @OnClick(R.id.edt_play_date)
    public void onPlayDateClick() {
        Timber.d("onPlayDateClick");
        setupDatePickerDialog();
    }

    @OnClick(R.id.edt_play_hour)
    public void onPlayHourClick() {
        Timber.d("onPlayHourClick");
        setupTimePickerDialog();
    }

    @OnClick(R.id.btn_map)
    public void onMapButtonClick() {
        SportField sportFieldModel = new SportField();
        sportFieldModel.setFieldId("123");
        sportFieldModel.setName("Hoa Binh");
        sportFieldModel.setPrice(500000);
        sportFieldModel.setAddress("Quan 12");
        sportFieldModel.setRating(4);
        sportFieldModel.setImgPath("https://cdn.pixabay.com/photo/2016/06/15/01/11/soccer-1457988_1280.jpg");
        presenter.saveSportFieldData(sportFieldModel);
    }

    private DatePickerDialog.OnDateSetListener dataPickerDialogListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            Timber.d("onDataSet: %d , %d , %d", i, i1, i2);
            String formattedDate = presenter.getFormattedDate(i, i1, i2);
            edtPlayDate.setText(formattedDate);
        }
    };


    private AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Timber.d("onItemSelected: %d", position);
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

}
