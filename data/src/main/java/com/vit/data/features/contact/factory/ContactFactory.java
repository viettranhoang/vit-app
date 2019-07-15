package com.vit.data.features.contact.factory;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ContactFactory {

    @Inject
    ContactCacheDataStore contactCacheDataStore;

    @Inject
    ContactRemoteDataStore contactRemoteDataStore;

    @Inject
    public ContactFactory(){
    }

    public ContactDataStore retrieveDataStore(boolean isCache) {
        if (isCache && !contactCacheDataStore.isExpired()) {
            return retrieveCacheDataStore();
        }
        return retrieveRemoteDataStore();
    }

    public ContactDataStore retrieveCacheDataStore() {
        return contactCacheDataStore;
    }

    public ContactDataStore retrieveRemoteDataStore() {
        return contactRemoteDataStore;
    }
}
