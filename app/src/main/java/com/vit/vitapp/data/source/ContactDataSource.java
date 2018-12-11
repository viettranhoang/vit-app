package com.vit.vitapp.data.source;

import com.vit.vitapp.data.ContactRepository;
import com.vit.vitapp.data.model.Contact;
import com.vit.vitapp.data.remote.source.ContactRemoteDataSource;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;

public class ContactDataSource implements ContactRepository {

    @Inject
    ContactRemoteDataSource remote;

    @Inject
    ContactDataSource() {
    }

    @Override
    public Flowable<List<Contact>> getContacts() {
        return remote.getContacts();
    }

    @Override
    public Observable<List<Contact>> searchContacts(String keyword) {
        return remote.searchContacts(keyword);
    }


}
