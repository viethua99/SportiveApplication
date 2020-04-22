package com.example.domain.interactor.sportfield;

import com.example.domain.executor.ExecutionThread;
import com.example.domain.interactor.base.MaybeUseCase;
import com.example.domain.repository.SportFieldRepository;

import javax.inject.Inject;

import io.reactivex.Maybe;

/**
 * Created by Viet Hua on 04/22/2020.
 */
public class GetSportFieldIdListByDistrictUseCase extends MaybeUseCase<String> {

    SportFieldRepository sportFieldRepository;

    @Inject
    public GetSportFieldIdListByDistrictUseCase(ExecutionThread executionThread, SportFieldRepository sportFieldRepository) {
        super(executionThread);
        this.sportFieldRepository = sportFieldRepository;
    }

    @Override
    protected Maybe buildUseCase(String param) {
        return sportFieldRepository.getSportFieldIdListByDistrict(param);
    }
}
