package com.example.domain.interactor.sportfield;

import com.example.domain.executor.ExecutionThread;
import com.example.domain.interactor.base.MaybeUseCase;
import com.example.domain.repository.SportFieldRepository;

import javax.inject.Inject;

import io.reactivex.Maybe;

/**
 * Created by Viet Hua on 4/11/2020
 */
public class GetSportFieldByIdUseCase extends MaybeUseCase<String> {

    SportFieldRepository sportFieldRepository;

    @Inject
    public GetSportFieldByIdUseCase(ExecutionThread executionThread, SportFieldRepository sportFieldRepository) {
        super(executionThread);
        this.sportFieldRepository = sportFieldRepository;
    }

    @Override
    protected Maybe buildUseCase(String param) {
        return sportFieldRepository.getSportFieldById(param);
    }
}
