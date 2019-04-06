package com.vit.data.features.contact.factory;

import com.vit.data.features.contact.model.ContactEntity;
import com.vit.data.features.contact.source.ContactCache;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Singleton
public class ContactCacheDataStore implements ContactDataStore {

    @Inject
    ContactCache contactCache;

    @Inject
    public ContactCacheDataStore() {
    }

    @Override
    public Completable saveContacts(List<ContactEntity> contacts) {
        return contactCache.saveContact(contacts)
                .doOnComplete(() -> contactCache.setLastCacheTime(System.currentTimeMillis()));
    }

    @Override
    public Flowable<List<ContactEntity>> getContacts() {
        return contactCache.getContacts();
    }

    @Override
    public Single<Boolean> isCached() {
        return contactCache.isCache();
    }

    @Override
    public boolean isExpired() {
        return contactCache.isExpired();
    }
}
