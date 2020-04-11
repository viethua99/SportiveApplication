package com.example.remote;

import com.example.data.entity.SportFieldEntity;
import com.example.data.repository.SportFieldDataRemote;
import com.example.domain.executor.ExecutionThread;
import com.example.remote.mapper.SportFieldModelMapper;
import com.example.remote.model.SportFieldModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeOnSubscribe;

/**
 * Created by Viet Hua on 04/11/2020.
 */
public class SportFieldDataRemoteImpl implements SportFieldDataRemote {
    private ExecutionThread executionThread;
    private SportFieldModelMapper sportFieldModelMapper;

    @Inject
    public SportFieldDataRemoteImpl(ExecutionThread executionThread) {
        this.executionThread = executionThread;
        sportFieldModelMapper = new SportFieldModelMapper();
    }

    @Override
    public Maybe<List<SportFieldEntity>> getSportFieldList() {
        return Maybe.create(new MaybeOnSubscribe<List<SportFieldEntity>>() {
            @Override
            public void subscribe(MaybeEmitter<List<SportFieldEntity>> emitter) throws Exception {
                emitter.onSuccess(sportFieldModelMapper.mapFromModels(testDataList()));
            }
        }).subscribeOn(executionThread.io());
    }

    private List<SportFieldModel> testDataList() {
        List<SportFieldModel> sportFieldModelList = new ArrayList<>();
        SportFieldModel sportFieldModel = new SportFieldModel();
        sportFieldModel.setFieldId("123");
        sportFieldModel.setName("Hoa Binh");
        sportFieldModel.setPrice(500000);
        sportFieldModel.setAddress("asdasda");
        sportFieldModel.setImgPath("https://cdn.pixabay.com/photo/2016/06/15/01/11/soccer-1457988_1280.jpg");
        sportFieldModelList.add(sportFieldModel);
        return sportFieldModelList;
    }
}
