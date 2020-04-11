package com.example.domain.interactor.sportfield;

import com.example.domain.executor.ExecutionThread;
import com.example.domain.interactor.base.MaybeUseCase;
import com.example.domain.model.EmptyParam;
import com.example.domain.repository.SportFieldRepository;

import javax.inject.Inject;

import io.reactivex.Maybe;

/**
 * Created by Viet Hua on 04/11/2020.
 */
public class GetSportFieldUseCase extends MaybeUseCase<EmptyParam> {
    SportFieldRepository sportFieldRepository;

    @Inject
    public GetSportFieldUseCase(ExecutionThread executionThread, SportFieldRepository sportFieldRepository) {
        super(executionThread);
        this.sportFieldRepository = sportFieldRepository;
    }

    @Override
    protected Maybe buildUseCase(EmptyParam param) {
        return sportFieldRepository.getSportFieldList();
    }
}
