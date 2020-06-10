package com.example.domain.interactor.minifield;

import com.example.domain.executor.ExecutionThread;
import com.example.domain.interactor.base.MaybeUseCase;
import com.example.domain.model.EmptyParam;
import com.example.domain.repository.MiniFieldRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Maybe;

/**
 * Created by Viet Hua on 06/10/2020.
 */
public class GetMiniFieldIdListUseCase extends MaybeUseCase<EmptyParam> {

    private MiniFieldRepository miniFieldRepository;

    @Inject
    public GetMiniFieldIdListUseCase(ExecutionThread executionThread, MiniFieldRepository miniFieldRepository) {
        super(executionThread);
        this.miniFieldRepository = miniFieldRepository;
    }

    @Override
    protected Maybe buildUseCase(EmptyParam param) {
        return miniFieldRepository.getMiniFieldIdList();
    }
}
