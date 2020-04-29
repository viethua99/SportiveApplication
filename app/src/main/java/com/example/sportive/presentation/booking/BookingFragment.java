package com.example.sportive.presentation.booking;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.domain.model.FieldBooking;
import com.example.sportive.R;
import com.example.sportive.presentation.base.BaseFragment;
import com.example.sportive.presentation.base.ItemClickListener;
import com.example.sportive.presentation.login.LoginActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import dagger.Binds;
import dagger.android.support.AndroidSupportInjection;
import timber.log.Timber;

/**
 * Created by Viet Hua on 04/27/2020.
 */
public class BookingFragment extends BaseFragment implements BookingContract.View {
    public static final String TAG = BookingFragment.class.getSimpleName();
    @BindView(R.id.ll_not_login)
    LinearLayout llNotlogin;


    @Inject
    BookingContract.Presenter presenter;

    Context mContext;
    private RecyclerView recyclerView;
    private BookingRecyclerViewAdapter bookingRecyclerViewAdapter;

    public static BookingFragment getInstance() {
        Timber.d("getInstance");
        BookingFragment bookingFragment = new BookingFragment();
        Bundle bundle = new Bundle();
        bookingFragment.setArguments(bundle);
        return bookingFragment;
    }

    @Override
    protected int getResLayoutId() {
        return R.layout.fragment_booking;
    }

    @Override
    protected void onMyCreatedView(View view) {
        Timber.d("onMyCreatedView");
        setUpRecyclerView(view);
    }

    @Override
    protected void onAttachToContext(Context context) {
        mContext = context;
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
        presenter.checkIfUserIsLoggedIn();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Timber.d("onDestroy");
        presenter.dropView();
    }

    @Override
    public void showNotLoginView() {
        llNotlogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void showBookingList(List<FieldBooking> fieldBookingList) {
        Timber.d("showBookingList");
        llNotlogin.setVisibility(View.INVISIBLE);
        bookingRecyclerViewAdapter.setData(fieldBookingList);

    }

    private void setUpRecyclerView(View view) {
        Timber.d("setupRecyclerView");
        recyclerView = view.findViewById(R.id.rv_booking);
        bookingRecyclerViewAdapter = new BookingRecyclerViewAdapter(getContext(), listener);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(bookingRecyclerViewAdapter);
    }

    private ItemClickListener listener = new ItemClickListener() {
        @Override
        public void onClickListener(int position) {

        }

        @Override
        public void onLongClickListener(int position) {

        }
    };


    @OnClick(R.id.btn_login)
    public void onLoginClick() {
        LoginActivity.startLoginActivity((AppCompatActivity) getActivity());
    }
}
