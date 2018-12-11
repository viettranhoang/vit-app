package com.vit.vitapp.di;


import com.vit.vitapp.data.ContactRepository;
import com.vit.vitapp.data.source.ContactDataSource;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract ContactRepository contactRepository(ContactDataSource contactDataSource);
}
