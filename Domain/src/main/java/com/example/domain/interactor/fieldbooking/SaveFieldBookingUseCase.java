package com.example.domain.interactor.fieldbooking;

import com.example.domain.executor.ExecutionThread;
import com.example.domain.interactor.base.CompletableUseCase;
import com.example.domain.model.FieldBooking;
import com.example.domain.repository.FieldBookingRepository;
import com.example.domain.repository.MiniFieldRepository;
import com.example.domain.utils.DomainUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Maybe;
import io.reactivex.functions.Function;

/**
 * Created by Viet Hua on 04/29/2020.
 */
public class SaveFieldBookingUseCase extends CompletableUseCase<FieldBooking> {

    FieldBookingRepository fieldBookingRepository;
    MiniFieldRepository miniFieldRepository;

    @Inject
    public SaveFieldBookingUseCase(ExecutionThread executionThread, FieldBookingRepository fieldBookingRepository,
                                   MiniFieldRepository miniFieldRepository) {
        super(executionThread);
        this.fieldBookingRepository = fieldBookingRepository;
        this.miniFieldRepository = miniFieldRepository;
    }

    @Override
    protected Completable buildUseCase(FieldBooking param) {
        return Maybe.zip(fieldBookingRepository.getFieldBookingList(), miniFieldRepository.getMiniFieldIdListById(param.getFieldId()), (fieldBookingList, miniFieldIdList) -> {
            List<String> overlappedSportFieldList = DomainUtils.getOverlappedSportFieldList(fieldBookingList, param.getStartTime(), param.getFinishTime());
            List<String> availableSportFieldIdList = miniFieldIdList;
            if (overlappedSportFieldList != null) {
                availableSportFieldIdList = DomainUtils.getAvailableSportFieldIdList(overlappedSportFieldList, miniFieldIdList);
            }
            return availableSportFieldIdList;
        }).flatMapCompletable(new Function<List<String>, CompletableSource>() {
            @Override
            public CompletableSource apply(List<String> strings) throws Exception {
                param.setFieldId(strings.get(0));
               return fieldBookingRepository.saveFieldBooking(param);
            }
        });
    }


}
