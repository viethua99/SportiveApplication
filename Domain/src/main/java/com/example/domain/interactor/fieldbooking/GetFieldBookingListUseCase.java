package com.example.domain.interactor.fieldbooking;

import com.example.domain.executor.ExecutionThread;
import com.example.domain.interactor.base.MaybeUseCase;
import com.example.domain.model.EmptyParam;
import com.example.domain.repository.FieldBookingRepository;

import javax.inject.Inject;

import io.reactivex.Maybe;

/**
 * Created by Viet Hua on 4/13/2020
 */
public class GetFieldBookingListUseCase extends MaybeUseCase<EmptyParam> {

    FieldBookingRepository fieldBookingRepository;

    @Inject
    public GetFieldBookingListUseCase(ExecutionThread executionThread, FieldBookingRepository fieldBookingRepository) {
        super(executionThread);
        this.fieldBookingRepository = fieldBookingRepository;
    }

    @Override
    protected Maybe buildUseCase(EmptyParam param) {
        return fieldBookingRepository.getFieldBookingList();
    }
}
