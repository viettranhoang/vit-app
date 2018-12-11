package com.vit.vitapp.di;


import com.vit.vitapp.data.remote.ApiService;
import com.vit.vitapp.data.remote.ApiServiceFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
abstract class  NetworkModule {

    @Singleton
    @Provides
    static ApiService apiService() {
        return new ApiServiceFactory().makeApiService();
    }
}
