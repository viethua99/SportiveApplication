package com.example.remote;

import com.example.data.entity.SportFieldEntity;
import com.example.data.repository.SportFieldDataRemote;
import com.example.remote.mapper.SportFieldModelMapper;
import com.example.remote.model.SportFieldModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import durdinapps.rxfirebase2.DataSnapshotMapper;
import durdinapps.rxfirebase2.RxFirebaseDatabase;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.functions.Function;


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
        Query query = firebaseDatabase.child(Constants.KEY_FIELD);
        return RxFirebaseDatabase.observeSingleValueEvent(query, DataSnapshotMapper.listOf(SportFieldModel.class))
                .map(new Function<List<SportFieldModel>, List<SportFieldEntity>>() {
                    @Override
                    public List<SportFieldEntity> apply(List<SportFieldModel> sportFieldModels) throws Exception {
                        return sportFieldModelMapper.mapFromModels(sportFieldModels);
                    }
                });
    }

    @Override
    public Maybe<SportFieldEntity> getSportFieldById(String id) {
        Query query = firebaseDatabase.child(Constants.KEY_FIELD).child(id);
        return RxFirebaseDatabase.observeSingleValueEvent(query, new Function<DataSnapshot, SportFieldEntity>() {
            @Override
            public SportFieldEntity apply(DataSnapshot dataSnapshot) throws Exception {
                SportFieldModel sportFieldModel = dataSnapshot.getValue(SportFieldModel.class);
                return sportFieldModelMapper.mapFromModel(sportFieldModel);
            }
        });
    }

    @Override
    public Completable addSportField(SportFieldEntity sportFieldEntity) {
        String key = firebaseDatabase.push().getKey();
        DatabaseReference fieldDatabase = firebaseDatabase.child(Constants.KEY_FIELD).child(key);
        Map<String, Object> data = new HashMap<>();
        data.put("fieldId", key);
        data.put("name", sportFieldEntity.getName());
        data.put("address", sportFieldEntity.getAddress());
        data.put("price", sportFieldEntity.getPrice());
        data.put("rating", sportFieldEntity.getRating());
        data.put("imgPath", sportFieldEntity.getImgPath());

        return RxFirebaseDatabase.setValue(fieldDatabase, data);
    }

}
