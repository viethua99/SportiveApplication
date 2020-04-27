package com.example.domain.interactor.auth;

import com.example.domain.executor.ExecutionThread;
import com.example.domain.interactor.base.CompletableUseCase;
import com.example.domain.interactor.base.MaybeUseCase;
import com.example.domain.repository.AuthenticationRepository;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Maybe;

/**
 * Created by Viet Hua on 04/27/2020.
 */
public class RegisterAccountUseCase extends MaybeUseCase<RegisterAccountUseCase.Param> {

    AuthenticationRepository authenticationRepository;

    @Inject
    public RegisterAccountUseCase(ExecutionThread executionThread, AuthenticationRepository authenticationRepository) {
        super(executionThread);
        this.authenticationRepository = authenticationRepository;
    }

    @Override
    protected Maybe buildUseCase(Param param) {
        return authenticationRepository.registerAccount(param.email, param.phoneNumber, param.password);
    }

    public static class Param {
        String email;
        String phoneNumber;
        String password;

        public Param(String email, String phoneNumber, String password) {
            this.email = email;
            this.phoneNumber = phoneNumber;
            this.password = password;
        }
    }

}
