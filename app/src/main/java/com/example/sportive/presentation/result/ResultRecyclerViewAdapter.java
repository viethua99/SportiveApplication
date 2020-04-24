package com.example.sportive.presentation.result;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.domain.model.EmptyParam;
import com.example.domain.model.SportField;
import com.example.sportive.R;
import com.example.sportive.presentation.base.BaseRecyclerViewAdapter;
import com.example.sportive.presentation.base.ItemClickListener;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import utils.SportiveUtils;

/**
 * Created by Viet Hua on 4/7/2020
 */
public class ResultRecyclerViewAdapter extends BaseRecyclerViewAdapter<SportField, ResultRecyclerViewAdapter.ViewHolder> {

    private ItemClickListener onDetailButtonClickListener;
    private ItemClickListener onBookingButtonClickListener;
    private int duration;

    public ResultRecyclerViewAdapter(Context context, ItemClickListener listener) {
        super(context);
        setListener(listener);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_result, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SportField sportField = mListData.get(position);
        holder.renderUI(sportField);
    }

    public void setButtonClickListener(ItemClickListener detailButtonClickListener, ItemClickListener bookingButtonClickListener) {
        this.onDetailButtonClickListener = detailButtonClickListener;
        this.onBookingButtonClickListener = bookingButtonClickListener;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        Button btnBooking, btnDetail;
        TextView tvFieldName, tvFieldLocation, tvTime, tvFieldPrice, tvTotalPrice;
        RatingBar ratingBar;
        ImageView imgField;

        public ViewHolder(View itemView) {
            super(itemView);
            btnBooking = itemView.findViewById(R.id.btn_field_booking);
            btnDetail = itemView.findViewById(R.id.btn_field_detail);
            tvFieldName = itemView.findViewById(R.id.txt_field_name);
            tvFieldLocation = itemView.findViewById(R.id.txt_field_location);
            tvTime = itemView.findViewById(R.id.txt_time);
            tvFieldPrice = itemView.findViewById(R.id.txt_field_price);
            tvTotalPrice = itemView.findViewById(R.id.txt_total_price);
            imgField = itemView.findViewById(R.id.img_field);
            ratingBar = itemView.findViewById(R.id.rating_bar);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (mListener == null) return;
            mListener.onClickListener(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View view) {
            if (mListener != null) {
                mListener.onLongClickListener(getAdapterPosition());
            }
            return false;
        }

        public void renderUI(SportField data) {
            tvFieldName.setText(data.getName());
            tvFieldLocation.setText(String.format("%s, %s", data.getSportFieldAddress().getStreet(), data.getSportFieldAddress().getDistrict()));
            tvFieldPrice.setText(SportiveUtils.getPricePerHourFormat(data.getPrice()));
            tvTotalPrice.setText(SportiveUtils.getTotalPriceFormat(data.getPrice() * duration));
            Glide.with(context).load(data.getImgPath()).into(imgField);
            ratingBar.setRating(data.getRating());

            btnDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onDetailButtonClickListener.onClickListener(getAdapterPosition());
                }
            });

            btnBooking.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBookingButtonClickListener.onClickListener(getAdapterPosition());
                }
            });
        }

    }

}
