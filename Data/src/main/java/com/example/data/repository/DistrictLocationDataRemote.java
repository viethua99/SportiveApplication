package com.example.data.repository;

import com.example.data.entity.DistrictLocationEntity;

import java.util.List;

import io.reactivex.Maybe;

/**
 * Created by Viet Hua on 4/19/2020
 */
public interface DistrictLocationDataRemote {
    Maybe<List<DistrictLocationEntity>> getDistrictLocationList();

}
