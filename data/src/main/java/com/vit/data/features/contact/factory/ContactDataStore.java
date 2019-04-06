package com.vit.data.features.contact.factory;

import com.vit.data.features.contact.model.ContactEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public interface ContactDataStore {

    Completable saveContacts(List<ContactEntity> contacts);

    Flowable<List<ContactEntity>> getContacts();

    Single<Boolean> isCached();

    boolean isExpired();
}
