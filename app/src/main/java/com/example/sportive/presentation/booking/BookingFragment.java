package com.example.sportive.presentation.booking;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.domain.model.FieldBooking;
import com.example.sportive.R;
import com.example.sportive.presentation.base.BaseFragment;
import com.example.sportive.presentation.base.ItemClickListener;
import com.example.sportive.presentation.bookingdetail.BookingDetailActivity;
import com.example.sportive.presentation.login.LoginActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import dagger.android.support.AndroidSupportInjection;
import timber.log.Timber;

/**
 * Created by Viet Hua on 04/27/2020.
 */
public class BookingFragment extends BaseFragment implements BookingContract.View {
    public static final String TAG = BookingFragment.class.getSimpleName();
    @BindView(R.id.ll_not_login)
    LinearLayout llNotlogin;
    @BindView(R.id.txt_empty_booking_message)
    TextView tvEmptyMessage;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;


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
        Timber.d("showNotLoginView");
        progressBar.setVisibility(View.INVISIBLE);
        llNotlogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void showBookingList(List<FieldBooking> fieldBookingList) {
        Timber.d("showBookingList");
        progressBar.setVisibility(View.INVISIBLE);
        llNotlogin.setVisibility(View.INVISIBLE);
        bookingRecyclerViewAdapter.setData(fieldBookingList);
    }

    @Override
    public void showEmptyListMessage() {
        Timber.d("showEmptyListMessage");
        progressBar.setVisibility(View.INVISIBLE);
        llNotlogin.setVisibility(View.INVISIBLE);
        tvEmptyMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public void showDeleteBookingSuccess() {
        Timber.d("showDeleteBookingSuccess");
        showToastMessage("Huỷ thành công");
        presenter.checkIfUserIsLoggedIn();
    }


    private void setUpRecyclerView(View view) {
        Timber.d("setupRecyclerView");
        recyclerView = view.findViewById(R.id.rv_booking);
        bookingRecyclerViewAdapter = new BookingRecyclerViewAdapter(getContext(), listener);
        bookingRecyclerViewAdapter.setOnButtonClickListener(shareButtonClickListener, cancelButtonClickListener);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(bookingRecyclerViewAdapter);
    }

    @Override
    public void refreshFragment() {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.detach(this).attach(this).commit();
    }

    private ItemClickListener listener = new ItemClickListener() {
        @Override
        public void onClickListener(int position) {
            Timber.d("onClickListener: %s", position);
            String fieldBookingId = bookingRecyclerViewAdapter.getItem(position).getBookingId();
            BookingDetailActivity.startBookingDetailActivity((AppCompatActivity) getActivity(), fieldBookingId);

        }

        @Override
        public void onLongClickListener(int position) {

        }
    };
    private ItemClickListener shareButtonClickListener = new ItemClickListener() {
        @Override
        public void onClickListener(int position) {
            Timber.d("onShareButtonClick: %s", position);
        }

        @Override
        public void onLongClickListener(int position) {

        }
    };

    private ItemClickListener cancelButtonClickListener = new ItemClickListener() {
        @Override
        public void onClickListener(int position) {
            Timber.d("onCancelButtonClick: %s", position);
            String bookingId = bookingRecyclerViewAdapter.getItem(position).getBookingId();
            presenter.deleteBookingById(bookingId);
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
