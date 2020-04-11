package com.example.domain.repository;

import com.example.domain.model.SportField;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;

/**
 * Created by Viet Hua on 04/11/2020.
 */
public interface SportFieldRepository {
    Maybe<List<SportField>> getSportFieldList();
    Completable addSportField(SportField sportField);
}
