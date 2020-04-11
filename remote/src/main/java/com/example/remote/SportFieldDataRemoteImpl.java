package com.example.remote;

import com.example.data.entity.SportFieldEntity;
import com.example.data.repository.SportFieldDataRemote;
import com.example.remote.mapper.SportFieldModelMapper;
import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import durdinapps.rxfirebase2.RxFirebaseDatabase;
import io.reactivex.Completable;
import io.reactivex.Maybe;


/**
 * Created by Viet Hua on 04/11/2020.
 */
public class SportFieldDataRemoteImpl implements SportFieldDataRemote {
    private SportFieldModelMapper sportFieldModelMapper;
    private DatabaseReference firebaseDatabase;

    @Inject
    public SportFieldDataRemoteImpl(DatabaseReference databaseReference) {
        sportFieldModelMapper = new SportFieldModelMapper();
        this.firebaseDatabase = databaseReference;
    }

    @Override
    public Maybe<List<SportFieldEntity>> getSportFieldList() {
        return null;
    }

    @Override
    public Completable addSportField(SportFieldEntity sportFieldEntity) {
        Map<String,Object> data = new HashMap<>();
        data.put("name",sportFieldEntity.getName());
        data.put("address",sportFieldEntity.getAddress());
        data.put("price",sportFieldEntity.getPrice());
        data.put("rating",sportFieldEntity.getRating());
        data.put("img",sportFieldEntity.getImgPath());

        return RxFirebaseDatabase.setValue(firebaseDatabase,data);
    }

}
