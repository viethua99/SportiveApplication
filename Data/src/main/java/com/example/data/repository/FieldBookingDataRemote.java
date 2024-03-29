package com.example.data.repository;

import com.example.data.entity.FieldBookingEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;

/**
 * Created by Viet Hua on 4/13/2020
 */
public interface FieldBookingDataRemote {

    Maybe<List<FieldBookingEntity>> getFieldBookingList();

    Maybe<List<FieldBookingEntity>> getFieldBookingListById(String userId);

    Maybe<FieldBookingEntity> getBookingDataById(String bookingId);

    Completable saveFieldBooking(FieldBookingEntity fieldBookingEntity);

    Completable deleteBookingById(String bookingId);
}
