package com.example.domain.interactor.sportfield;

import com.example.domain.executor.ExecutionThread;
import com.example.domain.interactor.base.CompletableUseCase;
import com.example.domain.model.SportField;
import com.example.domain.repository.SportFieldRepository;

import javax.inject.Inject;

import io.reactivex.Completable;

/**
 * Created by Viet Hua on 04/11/2020.
 */
public class AddSportFieldUsecase extends CompletableUseCase<SportField> {
    SportFieldRepository sportFieldRepository;


    @Inject
    public AddSportFieldUsecase(ExecutionThread executionThread, SportFieldRepository sportFieldRepository) {
        super(executionThread);
        this.sportFieldRepository = sportFieldRepository;
    }

    @Override
    protected Completable buildUseCase(SportField param) {
        return sportFieldRepository.addSportField(param);
    }
}
