package com.example.domain.interactor.fieldbooking;

import com.example.domain.executor.ExecutionThread;
import com.example.domain.interactor.base.CompletableUseCase;
import com.example.domain.repository.FieldBookingRepository;

import javax.inject.Inject;

import io.reactivex.Completable;

/**
 * Created by Viet Hua on 05/06/2020.
 */
public class DeleteBookingByIdUseCase extends CompletableUseCase<String> {


    FieldBookingRepository fieldBookingRepository;

    @Inject
    public DeleteBookingByIdUseCase(ExecutionThread executionThread, FieldBookingRepository fieldBookingRepository) {
        super(executionThread);
        this.fieldBookingRepository = fieldBookingRepository;
    }

    @Override
    protected Completable buildUseCase(String param) {
        return fieldBookingRepository.deleteBookingById(param);
    }
}
