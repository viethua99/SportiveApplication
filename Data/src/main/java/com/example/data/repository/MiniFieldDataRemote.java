package com.example.data.repository;

import com.example.data.entity.MiniFieldEntity;

import java.util.List;
import java.util.Set;

import io.reactivex.Maybe;
import io.reactivex.Observable;

/**
 * Created by Viet Hua on 06/10/2020.
 */
public interface MiniFieldDataRemote {
    Maybe<List<String>> getMiniFieldIdList();

    Observable<String> getSportFieldId(String miniFieldId);
}
