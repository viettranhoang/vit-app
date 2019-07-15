package com.vit.vitapp.di;


import com.vit.cache.source.ContactCacheImpl;
import com.vit.data.features.contact.ContactRepositoryImpl;
import com.vit.data.features.contact.source.ContactCache;
import com.vit.data.features.contact.source.ContactRemote;
import com.vit.domain.usecase.contact.repository.ContactRepository;
import com.vit.remote.features.contact.ContactRemoteImpl;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract ContactRepository contactRepository(ContactRepositoryImpl contactRepository);

    @Singleton
    @Binds
    abstract ContactCache contactCache(ContactCacheImpl contactCache);

    @Singleton
    @Binds
    abstract ContactRemote contactRemote(ContactRemoteImpl contactRemote);
}
