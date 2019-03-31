package com.vit.remote.common;

public interface Mapper<M, E> {
    E mapToEntity(M type);

    M mapToModel(E type);
}

