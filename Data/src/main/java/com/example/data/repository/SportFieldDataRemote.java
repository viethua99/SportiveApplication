package com.example.data.repository;

import com.example.data.entity.SportFieldEntity;

import java.util.List;

import io.reactivex.Maybe;

/**
 * Created by Viet Hua on 04/11/2020.
 */
public interface SportFieldDataRemote {
    Maybe<List<SportFieldEntity>> getSportFieldList();
}
