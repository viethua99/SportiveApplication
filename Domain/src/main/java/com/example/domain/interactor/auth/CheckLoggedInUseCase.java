package com.example.domain.interactor.auth;

import com.example.domain.executor.ExecutionThread;
import com.example.domain.interactor.base.MaybeUseCase;
import com.example.domain.model.EmptyParam;
import com.example.domain.model.IsLogged;
import com.example.domain.repository.AuthenticationRepository;
import com.example.domain.repository.UserInfoRepository;

import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.functions.Function;

/**
 * Created by Viet Hua on 04/29/2020.
 */
public class CheckLoggedInUseCase extends MaybeUseCase<EmptyParam> {

    AuthenticationRepository authenticationRepository;
    UserInfoRepository userInfoRepository;

    @Inject
    public CheckLoggedInUseCase(ExecutionThread executionThread,
                                AuthenticationRepository authenticationRepository,
                                UserInfoRepository userInfoRepository) {
        super(executionThread);
        this.authenticationRepository = authenticationRepository;
        this.userInfoRepository = userInfoRepository;
    }

    @Override
    protected Maybe buildUseCase(EmptyParam param) {
        return authenticationRepository.checkLoggedIn().flatMap(new Function<IsLogged, MaybeSource<?>>() {
            @Override
            public MaybeSource<?> apply(IsLogged isLogged) throws Exception {
                if (isLogged.isLogged()) {
                    return userInfoRepository.getUserInfoById(isLogged.getUserId());
                } else {
                    return Maybe.error(new Throwable("User is not logged in"));
                }
            }
        });
    }
}
