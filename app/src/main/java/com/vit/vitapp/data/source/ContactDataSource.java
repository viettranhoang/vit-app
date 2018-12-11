package com.vit.vitapp.data.source;

import com.vit.vitapp.data.ContactRepository;
import com.vit.vitapp.data.cache.source.ContactCacheDataSource;
import com.vit.vitapp.data.model.Contact;
import com.vit.vitapp.data.remote.source.ContactRemoteDataSource;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class ContactDataSource implements ContactRepository {

    @Inject
    ContactRemoteDataSource remote;

    @Inject
    ContactCacheDataSource cache;

    @Inject
    ContactDataSource() {
    }

    @Override
    public Flowable<List<Contact>> getContacts() {
        return Flowable.mergeDelayError(
                remote.getContacts()
                        .doOnNext(contacts -> cache.insertContacts(contacts))
                        .subscribeOn(Schedulers.io()),
                cache.getContacts().subscribeOn(Schedulers.io()));
    }

    @Override
    public Observable<List<Contact>> searchContacts(String keyword) {
        return Observable.mergeDelayError(
                remote.searchContacts(keyword)
                        .doOnNext(contacts -> cache.insertContacts(contacts))
                        .subscribeOn(Schedulers.io()),
                cache.searchContacts(keyword)
                        .subscribeOn(Schedulers.io())
        );
    }


}
