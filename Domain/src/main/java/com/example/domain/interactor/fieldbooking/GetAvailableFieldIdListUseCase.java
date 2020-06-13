package com.example.domain.interactor.fieldbooking;

import com.example.domain.executor.ExecutionThread;
import com.example.domain.interactor.base.MaybeUseCase;
import com.example.domain.model.EmptyParam;
import com.example.domain.model.FieldBooking;
import com.example.domain.model.SearchFieldConfig;
import com.example.domain.repository.FieldBookingRepository;
import com.example.domain.repository.MiniFieldRepository;

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
            List<String> overlappedSportFieldList = getOverlappedSportFieldList(fieldBookingList, searchFieldConfig.getStartTime(), searchFieldConfig.getFinishTime());
            List<String> availableSportFieldIdList = miniFieldIdList;
            if (overlappedSportFieldList != null) {
                availableSportFieldIdList = getAvailableSportFieldIdList(overlappedSportFieldList, miniFieldIdList);
            }
            return availableSportFieldIdList;
        });

    }

    private List<String> getOverlappedSportFieldList(List<FieldBooking> fieldBookingList, long startTime, long finishTime) {
        Set<String> overlappedSportFieldSet = new HashSet<>();
        for (FieldBooking fieldBooking : fieldBookingList) {
            if (fieldBooking.getStartTime() < finishTime && fieldBooking.getFinishTime() > startTime) {
                overlappedSportFieldSet.add(fieldBooking.getFieldId());
            }
        }

        List<String> overlappedSportFieldList = new ArrayList<>(overlappedSportFieldSet);
        return overlappedSportFieldList;
    }


    private List<String> getAvailableSportFieldIdList(List<String> overlappedSportFieldIdList, List<String> miniFieldIdList) {
        List<String> availableSportFieldIdList;
        availableSportFieldIdList = new ArrayList<>(miniFieldIdList);
        availableSportFieldIdList.addAll(overlappedSportFieldIdList);
        List<String> intersection = new ArrayList<>(miniFieldIdList);
        intersection.retainAll(overlappedSportFieldIdList);
        availableSportFieldIdList.removeAll(intersection);
        return availableSportFieldIdList;
    }
}
