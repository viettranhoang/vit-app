package com.vit.data.features.contact.factory;

import com.vit.data.features.contact.model.ContactEntity;
import com.vit.data.features.contact.source.ContactRemote;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Singleton
public class ContactRemoteDataStore implements ContactDataStore{

    @Inject
    ContactRemote contactRemote;

    @Inject
    public ContactRemoteDataStore() {
    }

    @Override
    public Completable saveContacts(List<ContactEntity> contacts) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Flowable<List<ContactEntity>> getContacts() {
        return contactRemote.getContacts();
    }

    @Override
    public Single<Boolean> isCached() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isExpired() {
        throw new UnsupportedOperationException();
    }
}
