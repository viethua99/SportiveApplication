package com.example.domain.executor;

import io.reactivex.Scheduler;

/**
 * Created by Viet Hua on 04/11/2020.
 */
public interface ExecutionThread {
    Scheduler main();

    Scheduler io();
}
