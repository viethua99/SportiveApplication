package com.example.domain.utils;

import com.example.domain.model.FieldBooking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Viet Hua on 06/13/2020.
 */
public class DomainUtils {

    public static List<String> getOverlappedSportFieldList(List<FieldBooking> fieldBookingList, long startTime, long finishTime) {
        Set<String> overlappedSportFieldSet = new HashSet<>();
        for (FieldBooking fieldBooking : fieldBookingList) {
            if (fieldBooking.getStartTime() < finishTime && fieldBooking.getFinishTime() > startTime) {
                overlappedSportFieldSet.add(fieldBooking.getMiniFieldId());
            }
        }

        List<String> overlappedSportFieldList = new ArrayList<>(overlappedSportFieldSet);
        return overlappedSportFieldList;
    }


    public static List<String> getAvailableSportFieldIdList(List<String> overlappedSportFieldIdList, List<String> miniFieldIdList) {
        List<String> availableSportFieldIdList;
        availableSportFieldIdList = new ArrayList<>(miniFieldIdList);
        availableSportFieldIdList.addAll(overlappedSportFieldIdList);
        List<String> intersection = new ArrayList<>(miniFieldIdList);
        intersection.retainAll(overlappedSportFieldIdList);
        availableSportFieldIdList.removeAll(intersection);
        return availableSportFieldIdList;
    }
}
