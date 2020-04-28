package com.example.domain.interactor.auth;

import com.example.domain.executor.ExecutionThread;
import com.example.domain.interactor.base.MaybeUseCase;
import com.example.domain.repository.AuthenticationRepository;
import com.example.domain.repository.UserInfoRepository;

import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.functions.Function;

/**
 * Created by Viet Hua on 04/27/2020.
 */
public class LoginUseCase extends MaybeUseCase<LoginUseCase.Param> {


    AuthenticationRepository authenticationRepository;
    UserInfoRepository userInfoRepository;

    @Inject
    public LoginUseCase(ExecutionThread executionThread, AuthenticationRepository authenticationRepository,
                        UserInfoRepository userInfoRepository) {
        super(executionThread);
        this.authenticationRepository = authenticationRepository;
        this.userInfoRepository = userInfoRepository;
    }

    @Override
    protected Maybe buildUseCase(Param param) {
        return authenticationRepository.loginWithEmailAndPassword(param.email, param.password)
                .flatMap(new Function<String, MaybeSource<?>>() {
                    @Override
                    public MaybeSource<?> apply(String userId) throws Exception {
                        return userInfoRepository.getUserInfoById(userId);
                    }
                });
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
