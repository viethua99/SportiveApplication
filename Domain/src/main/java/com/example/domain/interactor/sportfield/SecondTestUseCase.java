package com.example.domain.interactor.sportfield;

import com.example.domain.executor.ExecutionThread;
import com.example.domain.interactor.base.ObservableUseCase;
import com.example.domain.model.SportField;
import com.example.domain.repository.MiniFieldRepository;
import com.example.domain.repository.SportFieldRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * Created by Viet Hua on 6/10/2020
 */
public class SecondTestUseCase extends ObservableUseCase<List<String>> {

    private MiniFieldRepository miniFieldRepository;
    private SportFieldRepository sportFieldRepository;

    @Inject
    public SecondTestUseCase(ExecutionThread executionThread, MiniFieldRepository miniFieldRepository, SportFieldRepository sportFieldRepository) {
        super(executionThread);
        this.miniFieldRepository = miniFieldRepository;
        this.sportFieldRepository = sportFieldRepository;
    }

    @Override
    protected Observable<String> buildUseCase(List<String> param) {
        return Observable.fromIterable(param).flatMap(new Function<String, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(String s) throws Exception {

                return miniFieldRepository.getSportFieldId(s);
            }
        });
    }
}
