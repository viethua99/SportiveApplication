package com.example.data;

import com.example.data.entity.SportFieldEntity;
import com.example.data.mapper.SportFieldEntityMapper;
import com.example.data.repository.SportFieldDataRemote;
import com.example.domain.model.SportField;
import com.example.domain.repository.SportFieldRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.functions.Function;

/**
 * Created by Viet Hua on 04/11/2020.
 */
public class SportFieldRepositoryImpl implements SportFieldRepository {
    SportFieldDataRemote sportFieldDataRemote;
    SportFieldEntityMapper sportFieldEntityMapper;

    @Inject
    public SportFieldRepositoryImpl(SportFieldDataRemote sportFieldDataRemote) {
        this.sportFieldDataRemote = sportFieldDataRemote;
        sportFieldEntityMapper = new SportFieldEntityMapper();
    }

    @Override
    public Maybe<List<SportField>> getSportFieldList() {
        return sportFieldDataRemote.getSportFieldList().map(new Function<List<SportFieldEntity>, List<SportField>>() {
            @Override
            public List<SportField> apply(List<SportFieldEntity> sportFieldEntityList) throws Exception {
                return sportFieldEntityMapper.mapFromEntities(sportFieldEntityList);
            }
        });
    }

    @Override
    public Completable addSportField(SportField sportField) {
        return sportFieldDataRemote.addSportField(sportFieldEntityMapper.mapToEntity(sportField));
    }
}
