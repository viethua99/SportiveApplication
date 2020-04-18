package com.example.domain.interactor.base;

import com.example.domain.executor.ExecutionThread;

import io.reactivex.Completable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableCompletableObserver;

/**
 * Created by Viet Hua on 04/11/2020.
 */
public abstract class CompletableUseCase<P> {
    protected DisposableCompletableObserver disposableCompletableObserver;

    ExecutionThread executionThread;
    private CompositeDisposable compositeDisposable;

    public CompletableUseCase(ExecutionThread executionThread) {
        this.executionThread = executionThread;
        this.compositeDisposable = new CompositeDisposable();
    }

    protected abstract Completable buildUseCase(P param);

    public void execute(DisposableCompletableObserver disposableCompletableObserver, P param) {
        this.disposableCompletableObserver = disposableCompletableObserver;
        Completable completable = buildUseCase(param)
                .subscribeOn(executionThread.io())
                .observeOn(executionThread.main());
        addDisposable(completable.subscribeWith(disposableCompletableObserver));
    }

    public void dispose() {
        this.compositeDisposable.dispose();
    }

    private void addDisposable(Disposable disposable) {
        this.compositeDisposable.add(disposable);
    }


}
