package com.example.domain.interactor.fieldbooking;

import com.example.domain.executor.ExecutionThread;
import com.example.domain.interactor.base.CompletableUseCase;
import com.example.domain.model.FieldBooking;
import com.example.domain.repository.FieldBookingRepository;

import javax.inject.Inject;

import io.reactivex.Completable;

/**
 * Created by Viet Hua on 04/29/2020.
 */
public class SaveFieldBookingUseCase extends CompletableUseCase<FieldBooking> {

    FieldBookingRepository fieldBookingRepository;

    @Inject
    public SaveFieldBookingUseCase(ExecutionThread executionThread, FieldBookingRepository fieldBookingRepository) {
        super(executionThread);
        this.fieldBookingRepository = fieldBookingRepository;
    }

    @Override
    protected Completable buildUseCase(FieldBooking param) {
        return fieldBookingRepository.saveFieldBooking(param);
    }
}
