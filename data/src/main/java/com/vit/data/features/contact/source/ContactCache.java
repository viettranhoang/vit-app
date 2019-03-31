package com.vit.data.features.contact.source;

import com.vit.data.features.contact.model.ContactEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface ContactCache {

    Completable saveContact(List<ContactEntity> contacts);

    Flowable<List<ContactEntity>> getContacts();

    boolean isCache();

    boolean isExpired();

    void setLastCacheTime(long lastCacheTime);
}
