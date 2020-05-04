package com.example.domain.interactor.auth;

import com.example.domain.executor.ExecutionThread;
import com.example.domain.interactor.base.CompletableUseCase;
import com.example.domain.repository.AuthenticationRepository;

import javax.inject.Inject;

import io.reactivex.Completable;

/**
 * Created by Viet Hua on 05/04/2020.
 */
public class ResetPasswordUseCase extends CompletableUseCase<String> {

    AuthenticationRepository authenticationRepository;

    @Inject
    public ResetPasswordUseCase(ExecutionThread executionThread, AuthenticationRepository authenticationRepository) {
        super(executionThread);
        this.authenticationRepository = authenticationRepository;
    }

    @Override
    protected Completable buildUseCase(String param) {
        return authenticationRepository.resetPasswordFromEmail(param);
    }
}
