package com.example.sportive.presentation.location;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.domain.model.SportField;
import com.example.domain.model.TestModel;
import com.example.sportive.R;
import com.example.sportive.presentation.base.BaseRecyclerViewAdapter;
import com.example.sportive.presentation.base.ItemClickListener;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Viet Hua on 4/8/2020
 */
public class LocationRecyclerViewAdapter extends BaseRecyclerViewAdapter<TestModel, LocationRecyclerViewAdapter.ViewHolder> {
    public LocationRecyclerViewAdapter(Context context, ItemClickListener<TestModel> listener) {
        super(context);
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_location, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TestModel testModel = mListData.get(position);
        holder.renderUI(testModel);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView tvDistrict, tvCity, tvNumber;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            tvDistrict = itemView.findViewById(R.id.txt_district);
            tvCity = itemView.findViewById(R.id.txt_city);
            tvNumber = itemView.findViewById(R.id.txt_number);
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

        private void renderUI(TestModel data) {
            tvDistrict.setText(data.getDistrict());
            tvCity.setText(data.getCity());
            tvNumber.setText(data.getNumber());
        }
    }

}
