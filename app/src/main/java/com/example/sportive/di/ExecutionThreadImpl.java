package com.example.sportive.di;

import com.example.domain.executor.ExecutionThread;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Viet Hua on 04/11/2020.
 */
public class ExecutionThreadImpl implements ExecutionThread {
    @Override
    public Scheduler main() {
        return AndroidSchedulers.mainThread();
    }

    @Override
    public Scheduler io() {
        return Schedulers.io();
    }
}
