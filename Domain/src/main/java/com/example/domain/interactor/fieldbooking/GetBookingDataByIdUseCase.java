package com.example.domain.interactor.fieldbooking;

import com.example.domain.executor.ExecutionThread;
import com.example.domain.interactor.base.MaybeUseCase;
import com.example.domain.model.BookingDetail;
import com.example.domain.model.FieldBooking;
import com.example.domain.model.SportField;
import com.example.domain.repository.FieldBookingRepository;
import com.example.domain.repository.SportFieldRepository;

import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.MaybeSource;
import io.reactivex.functions.Function;

/**
 * Created by Viet Hua on 05/06/2020.
 */
public class GetBookingDataByIdUseCase extends MaybeUseCase<String> {

    FieldBookingRepository fieldBookingRepository;
    SportFieldRepository sportFieldRepository;

    @Inject
    public GetBookingDataByIdUseCase(ExecutionThread executionThread, FieldBookingRepository fieldBookingRepository,
                                     SportFieldRepository sportFieldRepository) {
        super(executionThread);
        this.fieldBookingRepository = fieldBookingRepository;
        this.sportFieldRepository = sportFieldRepository;
    }

    @Override
    protected Maybe<BookingDetail> buildUseCase(String param) {
        BookingDetail bookingDetail = new BookingDetail();
        return fieldBookingRepository.getBookingDataById(param)
                .flatMap((Function<FieldBooking, MaybeSource<BookingDetail>>) fieldBooking -> {
                    bookingDetail.setStartTime(fieldBooking.getStartTime());
                    bookingDetail.setFinishTime(fieldBooking.getFinishTime());
                    bookingDetail.setDuration(fieldBooking.getDuration());
                    bookingDetail.setTotalPrice(fieldBooking.getTotalPrice());
                    return sportFieldRepository.getSportFieldById(fieldBooking.getFieldId())
                            .flatMap((Function<SportField, MaybeSource<BookingDetail>>) sportField -> {
                                bookingDetail.setFieldName(sportField.getName());
                                bookingDetail.setFieldImagePath(sportField.getImgPath());
                                bookingDetail.setSportFieldAddress(sportField.getSportFieldAddress());
                                return Maybe.create((MaybeOnSubscribe<BookingDetail>) emitter -> emitter.onSuccess(bookingDetail));
                            });
                });
    }
}
