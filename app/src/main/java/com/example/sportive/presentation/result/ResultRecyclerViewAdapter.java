package com.example.sportive.presentation.result;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.domain.model.SportField;
import com.example.sportive.R;
import com.example.sportive.presentation.base.BaseRecyclerViewAdapter;
import com.example.sportive.presentation.base.ItemClickListener;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Viet Hua on 4/7/2020
 */
public class ResultRecyclerViewAdapter extends BaseRecyclerViewAdapter<SportField, ResultRecyclerViewAdapter.ViewHolder> {
    public ResultRecyclerViewAdapter(Context context, ItemClickListener<SportField> listener) {
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

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView tvFieldName, tvFieldLocation, tvTime, tvPrice;
        ImageView imgField;

        public ViewHolder(View itemView) {
            super(itemView);
            tvFieldName = itemView.findViewById(R.id.txt_field_name);
            tvFieldLocation = itemView.findViewById(R.id.txt_field_location);
            tvTime = itemView.findViewById(R.id.txt_time);
            tvPrice = itemView.findViewById(R.id.txt_price);
            imgField = itemView.findViewById(R.id.img_field);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (mListener == null) return;
            mListener.onClickListener(getAdapterPosition(), null);
        }

        @Override
        public boolean onLongClick(View view) {
            if (mListener != null) {
                mListener.onLongClickListener(getAdapterPosition(), null);
            }
            return false;
        }

        public void renderUI(SportField data) {
            tvFieldName.setText(data.getFieldName());
            tvFieldLocation.setText(data.getLocation());
            tvPrice.setText(data.getPrice());
            tvTime.setText(data.getTime());
            Glide.with(context).load(data.getImgUrl()).into(imgField);
        }

    }
}
