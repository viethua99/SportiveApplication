package com.example.domain.interactor.districtlocation;

import com.example.domain.executor.ExecutionThread;
import com.example.domain.interactor.base.MaybeUseCase;
import com.example.domain.model.EmptyParam;
import com.example.domain.repository.DistrictLocationRepository;

import javax.inject.Inject;

import io.reactivex.Maybe;

/**
 * Created by Viet Hua on 4/19/2020
 */
public class GetDistrictLocationListUseCase extends MaybeUseCase<EmptyParam> {

    DistrictLocationRepository districtLocationRepository;

    @Inject
    public GetDistrictLocationListUseCase(ExecutionThread executionThread, DistrictLocationRepository districtLocationRepository) {
        super(executionThread);
        this.districtLocationRepository = districtLocationRepository;
    }

    @Override
    protected Maybe buildUseCase(EmptyParam param) {
        return districtLocationRepository.getDistrictLocationList();
    }
}
