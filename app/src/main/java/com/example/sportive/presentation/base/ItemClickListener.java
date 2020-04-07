package com.example.sportive.presentation.base;

/**
 * Created by Viet Hua on 4/7/2020
 */
public interface ItemClickListener<T> {
    void onClickListener(int position, T t);

    void onLongClickListener(int position, T t);
}
