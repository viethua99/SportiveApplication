package com.example.domain.interactor.auth;

import com.example.domain.executor.ExecutionThread;
import com.example.domain.interactor.base.CompletableUseCase;
import com.example.domain.interactor.base.MaybeUseCase;
import com.example.domain.model.UserInfo;
import com.example.domain.repository.AuthenticationRepository;
import com.example.domain.repository.UserInfoRepository;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.functions.Function;

/**
 * Created by Viet Hua on 04/27/2020.
 */
public class RegisterAccountUseCase extends MaybeUseCase<RegisterAccountUseCase.Param> {

    AuthenticationRepository authenticationRepository;
    UserInfoRepository userInfoRepository;

    @Inject
    public RegisterAccountUseCase(ExecutionThread executionThread, AuthenticationRepository authenticationRepository,
                                  UserInfoRepository userInfoRepository) {
        super(executionThread);
        this.authenticationRepository = authenticationRepository;
        this.userInfoRepository = userInfoRepository;
    }

    @Override
    protected Maybe buildUseCase(Param param) {
        return authenticationRepository.registerAccount(param.email, param.password)
                .flatMap(new Function<String, MaybeSource<?>>() {
                    @Override
                    public MaybeSource<?> apply(String userId) throws Exception {
                        UserInfo userInfo = new UserInfo(userId, param.username, param.phoneNumber, param.email);
                        return userInfoRepository.saveUserInfo(userInfo).flatMap(new Function<String, MaybeSource<?>>() {
                            @Override
                            public MaybeSource<?> apply(String s) throws Exception {
                                return authenticationRepository.loginWithEmailAndPassword(param.email, param.password) //Auto login
                                        .flatMap(new Function<String, MaybeSource<?>>() {
                                            @Override
                                            public MaybeSource<?> apply(String userId) throws Exception {
                                                return userInfoRepository.getUserInfoById(userId);
                                            }
                                        });
                            }
                        });
                    }
                });
    }

    public static class Param {
        String username;
        String email;
        String phoneNumber;
        String password;

        public Param(String username, String email, String phoneNumber, String password) {
            this.username = username;
            this.email = email;
            this.phoneNumber = phoneNumber;
            this.password = password;
        }
    }

}
