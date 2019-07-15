package com.vit.data.features.contact.source;

import com.vit.data.features.contact.model.ContactEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public interface ContactCache {

    Completable saveContact(List<ContactEntity> contacts);

    Flowable<List<ContactEntity>> getContacts();

    Single<Boolean> isCache();

    boolean isExpired();

    void setLastCacheTime(long lastCacheTime);
}
