package com.example.domain.interactor.auth;

import com.example.domain.executor.ExecutionThread;
import com.example.domain.interactor.base.MaybeUseCase;
import com.example.domain.model.EmptyParam;
import com.example.domain.repository.AuthenticationRepository;

import javax.inject.Inject;

import io.reactivex.Maybe;

/**
 * Created by Viet Hua on 04/29/2020.
 */
public class CheckLoggedInUseCase extends MaybeUseCase<EmptyParam> {

    AuthenticationRepository authenticationRepository;

    @Inject
    public CheckLoggedInUseCase(ExecutionThread executionThread, AuthenticationRepository authenticationRepository) {
        super(executionThread);
        this.authenticationRepository = authenticationRepository;
    }

    @Override
    protected Maybe buildUseCase(EmptyParam param) {
        return authenticationRepository.checkLoggedIn();
    }
}
