package com.example.domain.repository;

import com.example.domain.interactor.base.MaybeUseCase;
import com.example.domain.model.MiniField;

import java.util.List;
import java.util.Set;

import io.reactivex.Maybe;
import io.reactivex.Observable;

/**
 * Created by Viet Hua on 06/10/2020.
 */
public interface MiniFieldRepository {
    Maybe<List<String>> getMiniFieldIdList();
    Observable<String> getSportFieldId(String miniFieldList);
}
