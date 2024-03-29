package com.example.sportive.presentation.home;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.domain.model.DistrictLocation;
import com.example.domain.model.SearchFieldConfig;
import com.example.sportive.R;
import com.example.sportive.myapp.MyApp;
import com.example.sportive.presentation.base.BaseFragment;
import com.example.sportive.presentation.location.LocationActivity;
import com.example.sportive.presentation.map.MapActivity;
import com.example.sportive.presentation.result.ResultActivity;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.OnClick;
import dagger.android.support.AndroidSupportInjection;
import timber.log.Timber;
import utils.SportiveUtils;

/**
 * Created By Viet Hua on 4/7/2020
 */
public class HomeFragment extends BaseFragment implements HomeContract.View {
    public static final String TAG = HomeFragment.class.getSimpleName();
    private final Calendar myCalendar = Calendar.getInstance();
    @BindView(R.id.edt_location)
    EditText edtLocation;
    @BindView(R.id.edt_play_hour)
    EditText edtPlayHour;
    @BindView(R.id.edt_play_date)
    EditText edtPlayDate;
    @BindView(R.id.spinner_duration)
    Spinner durationSpinner;

    TimePickerDialog timePickerDialog;
    DatePickerDialog datePickerDialog;

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
        setupTimePickerDialog();
        setupDatePickerDialog();
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
        edtPlayDate.setText(presenter.getFormattedDate(0, 0, 0));  //show current date for the first time
        edtPlayHour.setText(presenter.getFormattedHour(0)); //show (current hour+1) for the first time
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Timber.d("onActivityResult");
        if (requestCode == LocationActivity.DISTRICT_LOCATION_REQUEST_CODE) {
            if (resultCode == getActivity().RESULT_OK) {
                DistrictLocation districtLocation = (DistrictLocation) data.getSerializableExtra(LocationActivity.KEY_DISTRICT_LOCATION);
                edtLocation.setText(SportiveUtils.convertShortNameFormatToFullName(districtLocation.getName()));
                presenter.saveDistrictLocation(districtLocation.getLatitude(), districtLocation.getLongitude(), districtLocation.getName());
            }
        }
    }

    private void setupDatePickerDialog() {
        Timber.d("setupDatePickerDialog");
        setFixedLanguageToVietnamese();
        datePickerDialog = new DatePickerDialog(Objects.requireNonNull(getContext()), R.style.DialogTheme, dataPickerDialogListener,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000); //Disable past dates
    }

    //Set all datepicker / timerpick dialogs language to Vietnamese
    private void setFixedLanguageToVietnamese() {
        Timber.d("setFixedLanguageToVietnamese");
        Locale locale = new Locale("vi", "VN");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getContext().getResources().updateConfiguration(config, null);
    }

    private void setupTimePickerDialog() {
        Timber.d("setupTimePickerDialog");
        timePickerDialog = TimePickerDialog.newInstance(onTimeSetListener,
                myCalendar.HOUR_OF_DAY,
                myCalendar.MINUTE,
                false);
        timePickerDialog.setTitle(getString(R.string.choose_play_time));
        timePickerDialog.enableMinutes(false);

    }

    private void setupDurationSpinner() {
        Timber.d("setupDurationSpinner");
        durationSpinner.setOnItemSelectedListener(onItemSelectedListener);
    }

    @OnClick(R.id.btn_search)
    public void onSearchClick() {
        Timber.d("onSearchClick");
        SearchFieldConfig searchFieldConfig = new SearchFieldConfig(
                presenter.getDurationTime(),
                presenter.getStartTime(),
                presenter.getFinishTime(),
                presenter.getLatitude(),
                presenter.getLongitude(),
                presenter.getDistrictName()
        );
        ResultActivity.startResultActivity((AppCompatActivity) getActivity(), searchFieldConfig);

    }

    @OnClick(R.id.btn_select)
    public void onSelectClick() {
        Timber.d("onSelectClick");

    }

    @OnClick(R.id.btn_current_location)
    public void onCurrentLocationClick() {
        Timber.d("onCurrentLocationClick");
        edtLocation.setText(getString(R.string.field_near_you));
    }

    @OnClick(R.id.edt_location)
    public void onLocationClick() {
        Timber.d("onLocationClick");
        LocationActivity.startLocationActivityForResult(this);

    }


    @OnClick(R.id.edt_play_date)
    public void onPlayDateClick() {
        Timber.d("onPlayDateClick");
        datePickerDialog.show();
    }

    @OnClick(R.id.edt_play_hour)
    public void onPlayHourClick() {
        Timber.d("onPlayHourClick");
        timePickerDialog.show(Objects.requireNonNull(getFragmentManager()), "TimePickerDialog");
    }

    @OnClick(R.id.btn_map)
    public void onMapButtonClick() {
        MapActivity.startMapActivity((AppCompatActivity) getActivity());
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
            int duration = position + 1;
            presenter.saveDurationTime(duration);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
            Timber.d("onTimeSet: %dh %dm %ds", hourOfDay, minute, second);
            edtPlayHour.setText(presenter.getFormattedHour(hourOfDay));
        }
    };

}
