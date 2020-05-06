package com.example.sportive.presentation.booking;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.domain.model.FieldBooking;
import com.example.sportive.R;
import com.example.sportive.presentation.base.BaseRecyclerViewAdapter;
import com.example.sportive.presentation.base.ItemClickListener;

import utils.SportiveUtils;
import utils.TimeUtils;

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

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        ImageView imgField;
        TextView tvFieldName, tvPlayDate, tvPlayTime, tvTotalPrice;

        public ViewHolder(View view) {
            super(view);
            imgField = view.findViewById(R.id.img_field);
            tvFieldName = view.findViewById(R.id.txt_field_name);
            tvPlayDate = view.findViewById(R.id.txt_play_date);
            tvPlayTime = view.findViewById(R.id.txt_play_time);
            tvTotalPrice = view.findViewById(R.id.txt_total_price);
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mListener.onClickListener(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            mListener.onLongClickListener(getAdapterPosition());
            return true;
        }

        private void renderUI(FieldBooking fieldBooking) {
            Glide.with(context).load(fieldBooking.getFieldImg()).into(imgField);
            tvFieldName.setText(fieldBooking.getFieldName());
            tvPlayDate.setText(String.format("Ngày đá: %s", TimeUtils.convertMillisecondsToDateFormat(fieldBooking.getStartTime())));
            tvTotalPrice.setText(SportiveUtils.getTotalPriceFormat(fieldBooking.getTotalPrice()));
            tvPlayTime.setText(String.format("Giờ chơi: %s - %s",
                    TimeUtils.convertMillisecondsToHourFormat(fieldBooking.getStartTime()),
                    TimeUtils.convertMillisecondsToHourFormat(fieldBooking.getFinishTime())));
        }
    }

}
