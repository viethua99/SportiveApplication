package com.example.domain.interactor.fieldbooking;

import com.example.domain.executor.ExecutionThread;
import com.example.domain.interactor.base.MaybeUseCase;
import com.example.domain.model.EmptyParam;
import com.example.domain.model.FieldBooking;
import com.example.domain.model.SearchFieldConfig;
import com.example.domain.repository.FieldBookingRepository;
import com.example.domain.repository.MiniFieldRepository;
import com.example.domain.utils.DomainUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;

/**
 * Created by Viet Hua on 6/10/2020
 */
public class GetAvailableFieldIdListUseCase extends MaybeUseCase<SearchFieldConfig> {

    private FieldBookingRepository fieldBookingRepository;
    private MiniFieldRepository miniFieldRepository;

    @Inject
    public GetAvailableFieldIdListUseCase(ExecutionThread executionThread, FieldBookingRepository fieldBookingRepository, MiniFieldRepository miniFieldRepository) {
        super(executionThread);
        this.fieldBookingRepository = fieldBookingRepository;
        this.miniFieldRepository = miniFieldRepository;
    }

    @Override
    protected Maybe buildUseCase(SearchFieldConfig searchFieldConfig) {
        return Maybe.zip(fieldBookingRepository.getFieldBookingList(), miniFieldRepository.getMiniFieldIdList(), (fieldBookingList, miniFieldIdList) -> {
            List<String> overlappedSportFieldList = DomainUtils.getOverlappedSportFieldList(fieldBookingList, searchFieldConfig.getStartTime(), searchFieldConfig.getFinishTime());
            List<String> availableSportFieldIdList = miniFieldIdList;
            if (overlappedSportFieldList != null) {
                availableSportFieldIdList = DomainUtils.getAvailableSportFieldIdList(overlappedSportFieldList, miniFieldIdList);
            }
            return availableSportFieldIdList;
        });

    }

}
