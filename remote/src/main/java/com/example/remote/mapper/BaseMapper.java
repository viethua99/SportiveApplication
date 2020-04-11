package com.example.remote.mapper;

/**
 * Created by Viet Hua on 04/11/2020.
 */
public interface BaseMapper<E,M> {
    M mapToModel(E e);
    E mapFromModel(M m);
}
