package com.example.data;

import com.example.data.repository.MiniFieldDataRemote;
import com.example.domain.model.MiniField;
import com.example.domain.repository.MiniFieldRepository;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.Observable;

/**
 * Created by Viet Hua on 06/10/2020.
 */
public class MiniFieldRepositoryImpl implements MiniFieldRepository {

    private MiniFieldDataRemote miniFieldDataRemote;

    @Inject
    public MiniFieldRepositoryImpl(MiniFieldDataRemote miniFieldDataRemote) {
        this.miniFieldDataRemote = miniFieldDataRemote;
    }


    @Override
    public Maybe<List<String>> getMiniFieldIdList() {
        return miniFieldDataRemote.getMiniFieldIdList();
    }

    @Override
    public Observable<String> getSportFieldId(String miniFieldList) {
        return miniFieldDataRemote.getSportFieldId(miniFieldList);
    }
}
