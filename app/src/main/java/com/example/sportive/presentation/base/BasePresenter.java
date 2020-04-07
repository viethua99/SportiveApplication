package com.example.sportive.presentation.base;

/**
 * Created by Viet Hua on 4/7/2020
 */
public interface BasePresenter<V> {
    void attachView(V view);

    void dropView();
}
