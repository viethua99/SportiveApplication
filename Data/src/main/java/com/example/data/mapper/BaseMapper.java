package com.example.data.mapper;

/**
 * Created by Viet Hua on 04/11/2020.
 */
public interface BaseMapper<E,M> {
    E mapToEntity(M m);
    M mapFromEntity(E e);

}
