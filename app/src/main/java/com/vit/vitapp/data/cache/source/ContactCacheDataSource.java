package com.vit.vitapp.data.cache.source;

import com.vit.vitapp.data.ContactRepository;
import com.vit.vitapp.data.cache.AppDatabase;
import com.vit.vitapp.data.model.Contact;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;

public class ContactCacheDataSource implements ContactRepository {

    @Inject
    AppDatabase database;

    @Inject
    ContactCacheDataSource(){
    }

    @Override
    public Flowable<List<Contact>> getContacts() {
        return database.contactDao().getContacts();
    }

    /*@Override not run ???
    public Observable<List<Contact>> searchContacts(String keyword) {
        return database.contactDao().searchContacts(keyword)
                .toObservable();
    }*/

    @Override
    public Observable<List<Contact>> searchContacts(String keyword) {
        return database.contactDao().getContacts()
                .map(contacts -> Observable.fromIterable(contacts)
                        .filter(contact -> contact.getName().toLowerCase().contains(keyword.toLowerCase()))
                        .toList()
                        .blockingGet())
                .toObservable();
    }

    public void insertContacts(List<Contact> contacts) {
        database.contactDao().insert(contacts);
    }
}
