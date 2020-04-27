package com.example.domain.interactor.auth;

import com.example.domain.executor.ExecutionThread;
import com.example.domain.interactor.base.MaybeUseCase;
import com.example.domain.repository.AuthenticationRepository;

import javax.inject.Inject;

import io.reactivex.Maybe;

/**
 * Created by Viet Hua on 04/27/2020.
 */
public class LoginUseCase extends MaybeUseCase<LoginUseCase.Param> {


    AuthenticationRepository authenticationRepository;

    @Inject
    public LoginUseCase(ExecutionThread executionThread, AuthenticationRepository authenticationRepository) {
        super(executionThread);
        this.authenticationRepository = authenticationRepository;
    }

    @Override
    protected Maybe buildUseCase(Param param) {
        return authenticationRepository.loginWithEmailAndPassword(param.email, param.password);
    }

    public static class Param {
        String email;
        String password;

        public Param(String email, String password) {
            this.email = email;
            this.password = password;
        }
    }
}
