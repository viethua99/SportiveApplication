package com.example.domain.interactor.base;

import com.example.domain.executor.ExecutionThread;

import io.reactivex.Maybe;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableMaybeObserver;

/**
 * Created by Viet Hua on 04/11/2020.
 */
public abstract class MaybeUseCase<P> {
    protected DisposableMaybeObserver disposableMaybeObserver;
    ExecutionThread executionThread;
    private CompositeDisposable compositeDisposable;

    public MaybeUseCase(ExecutionThread executionThread) {
        this.executionThread = executionThread;
        this.compositeDisposable = new CompositeDisposable();
    }

    protected abstract Maybe buildUseCase(P param);

    public void execute(DisposableMaybeObserver disposableMaybeObserver, P param) {
        this.disposableMaybeObserver = disposableMaybeObserver;
        Maybe maybe = buildUseCase(param)
                .subscribeOn(executionThread.io())
                .observeOn(executionThread.main());
        addDisposable((Disposable) maybe.subscribeWith(disposableMaybeObserver));

    }

    public void dispose() {
        this.compositeDisposable.dispose();
    }

    private void addDisposable(Disposable disposable) {
        this.compositeDisposable.add(disposable);
    }
}
