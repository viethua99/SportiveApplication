package com.example.domain.interactor.auth;

import com.example.domain.executor.ExecutionThread;
import com.example.domain.interactor.base.CompletableUseCase;
import com.example.domain.model.EmptyParam;
import com.example.domain.repository.AuthenticationRepository;

import javax.inject.Inject;

import io.reactivex.Completable;

/**
 * Created by Viet Hua on 04/29/2020.
 */
public class LogoutUseCase extends CompletableUseCase<EmptyParam> {

    AuthenticationRepository authenticationRepository;

    @Inject
    public LogoutUseCase(ExecutionThread executionThread, AuthenticationRepository authenticationRepository) {
        super(executionThread);
        this.authenticationRepository = authenticationRepository;
    }

    @Override
    protected Completable buildUseCase(EmptyParam param) {
        return authenticationRepository.logout();
    }
}
