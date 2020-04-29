package com.example.sportive.presentation.booking;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.domain.model.FieldBooking;
import com.example.sportive.R;
import com.example.sportive.presentation.base.BaseRecyclerViewAdapter;
import com.example.sportive.presentation.base.ItemClickListener;

/**
 * Created by Viet Hua on 04/29/2020.
 */
public class BookingRecyclerViewAdapter extends BaseRecyclerViewAdapter<FieldBooking, BookingRecyclerViewAdapter.ViewHolder> {

    public BookingRecyclerViewAdapter(Context context, ItemClickListener listener) {
        super(context);
        this.mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_booking, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FieldBooking fieldBooking = mListData.get(position);
        holder.renderUI(fieldBooking);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvStartTime, tvFinishTime;

        public ViewHolder(View view) {
            super(view);
            tvStartTime = view.findViewById(R.id.txt_start_time);
            tvFinishTime = view.findViewById(R.id.txt_finish_time);
        }

        private void renderUI(FieldBooking fieldBooking) {
            tvStartTime.setText(String.valueOf(fieldBooking.getStartTime()));
            tvFinishTime.setText(String.valueOf(fieldBooking.getFinishTime()));
        }
    }

}
