package com.example.domain.interactor.fieldbooking;

import com.example.domain.executor.ExecutionThread;
import com.example.domain.interactor.base.MaybeUseCase;
import com.example.domain.repository.FieldBookingRepository;

import javax.inject.Inject;

import io.reactivex.Maybe;

/**
 * Created by Viet Hua on 05/06/2020.
 */
public class GetBookingDataByIdUseCase extends MaybeUseCase<String> {

    FieldBookingRepository fieldBookingRepository;

    @Inject
    public GetBookingDataByIdUseCase(ExecutionThread executionThread, FieldBookingRepository fieldBookingRepository) {
        super(executionThread);
        this.fieldBookingRepository = fieldBookingRepository;
    }

    @Override
    protected Maybe buildUseCase(String param) {
        return fieldBookingRepository.getBookingDataById(param);
    }
}
