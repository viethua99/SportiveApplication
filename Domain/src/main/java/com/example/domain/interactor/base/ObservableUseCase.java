package com.example.domain.interactor.base;


import com.example.domain.executor.ExecutionThread;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by Viet Hua on 4/13/2020
 */
public abstract class ObservableUseCase<P> {
    ExecutionThread executionThread;
    private CompositeDisposable compositeDisposable;

    public ObservableUseCase(ExecutionThread executionThread) {
        this.executionThread = executionThread;
        this.compositeDisposable = new CompositeDisposable();
    }

    protected abstract Observable buildUseCase(P param);

    public void execute(DisposableObserver disposableObserver, P param) {
        Observable observable = buildUseCase(param)
                .subscribeOn(executionThread.io())
                .observeOn(executionThread.main());
        addDisposable((Disposable) observable.subscribeWith(disposableObserver));
    }

    public void dispose(){
        this.compositeDisposable.dispose();
    }

    private void addDisposable(Disposable disposable) {
        this.compositeDisposable.add(disposable);
    }
}
