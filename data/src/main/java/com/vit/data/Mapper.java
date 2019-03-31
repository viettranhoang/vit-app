package com.vit.data;

public interface Mapper<M, E> {

    E mapToEntity(M type);

    M mapFromEntity(E type);
}
