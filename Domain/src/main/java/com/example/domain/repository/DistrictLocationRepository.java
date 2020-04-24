package com.example.domain.repository;

import com.example.domain.model.DistrictLocation;

import java.util.List;

import io.reactivex.Maybe;

/**
 * Created by Viet Hua on 4/19/2020
 */
public interface DistrictLocationRepository {
    Maybe<List<DistrictLocation>> getDistrictLocationList();
}
