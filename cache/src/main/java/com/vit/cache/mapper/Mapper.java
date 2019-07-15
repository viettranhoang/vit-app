package com.vit.cache.mapper;


public interface Mapper<C, E> {

    E mapFromCached(C type);

    C mapToCached(E type);

}
