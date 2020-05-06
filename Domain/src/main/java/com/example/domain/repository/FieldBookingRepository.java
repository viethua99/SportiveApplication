package com.example.domain.repository;

import com.example.domain.model.FieldBooking;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;

/**
 * Created by Viet Hua on 4/13/2020
 */
public interface FieldBookingRepository {
    Maybe<List<FieldBooking>> getFieldBookingList();

    Maybe<List<FieldBooking>> getFieldBookingListById(String userId);

    Maybe<FieldBooking> getBookingDataById(String bookingId);

    Completable saveFieldBooking(FieldBooking fieldBooking);

    Completable deleteBookingById(String bookingId);
}
