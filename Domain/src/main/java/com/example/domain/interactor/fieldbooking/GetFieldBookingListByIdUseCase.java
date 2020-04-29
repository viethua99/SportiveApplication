package com.example.domain.interactor.fieldbooking;

import com.example.domain.executor.ExecutionThread;
import com.example.domain.interactor.base.MaybeUseCase;
import com.example.domain.model.FieldBooking;
import com.example.domain.repository.FieldBookingRepository;
import com.example.domain.repository.SportFieldRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.functions.Function;

/**
 * Created by Viet Hua on 04/28/2020.
 */
public class GetFieldBookingListByIdUseCase extends MaybeUseCase<String> {

    FieldBookingRepository fieldBookingRepository;

    @Inject
    public GetFieldBookingListByIdUseCase(ExecutionThread executionThread,
                                          FieldBookingRepository fieldBookingRepository) {
        super(executionThread);
        this.fieldBookingRepository = fieldBookingRepository;

    }

    @Override
    protected Maybe buildUseCase(String param) {
        return fieldBookingRepository.getFieldBookingListById(param);
    }
}
