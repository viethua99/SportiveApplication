package com.example.remote;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.data.repository.MiniFieldDataRemote;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import durdinapps.rxfirebase2.RxFirebaseDatabase;
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Function;

/**
 * Created by Viet Hua on 06/10/2020.
 */
public class MiniFieldDataRemoteImpl implements MiniFieldDataRemote {

    private DatabaseReference databaseReference;

    @Inject
    public MiniFieldDataRemoteImpl(DatabaseReference databaseReference) {
        this.databaseReference = databaseReference;
    }

    @Override
    public Maybe<List<String>> getMiniFieldIdList() {
        Query query = databaseReference.child(Constants.KEY_MINI_FIELDS);
        return RxFirebaseDatabase.observeSingleValueEvent(query, new Function<DataSnapshot, List<String>>() {
            @Override
            public List<String> apply(DataSnapshot dataSnapshot) throws Exception {
                List<String> miniFieldId = new ArrayList<>();
                for (DataSnapshot subSnapshot : dataSnapshot.getChildren()) {
                    miniFieldId.add(subSnapshot.getKey());
                }
                return miniFieldId;
            }
        });
    }

    private Set<String> test = new HashSet<>();

    @Override
    public Observable<String> getSportFieldId(final String miniFieldId) {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> emitter) throws Exception {
                databaseReference.child(Constants.KEY_MINI_FIELDS).child(miniFieldId).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            String fieldId = dataSnapshot.child("fieldId").getValue(String.class);
                            if(!test.contains(fieldId)){
                                test.add(fieldId);
                                emitter.onNext(fieldId);
                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        emitter.onError(new Throwable(databaseError.getMessage()));
                    }
                });
            }
        });
    }
}
