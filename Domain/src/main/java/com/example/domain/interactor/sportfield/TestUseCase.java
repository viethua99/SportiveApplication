package com.example.domain.interactor.sportfield;

import com.example.domain.executor.ExecutionThread;
import com.example.domain.interactor.base.MaybeUseCase;
import com.example.domain.interactor.base.ObservableUseCase;
import com.example.domain.repository.MiniFieldRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.Observable;

/**
 * Created by Viet Hua on 06/10/2020.
 */
public class TestUseCase extends ObservableUseCase<String> {

    private MiniFieldRepository miniFieldRepository;

    @Inject
    public TestUseCase(ExecutionThread executionThread, MiniFieldRepository miniFieldRepository) {
        super(executionThread);
        this.miniFieldRepository = miniFieldRepository;
    }

    @Override
    protected Observable buildUseCase(String param) {
        return miniFieldRepository.getSportFieldId(param);
    }
}
