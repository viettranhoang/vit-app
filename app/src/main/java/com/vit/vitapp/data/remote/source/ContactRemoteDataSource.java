package com.vit.vitapp.data.remote.source;

import com.vit.vitapp.data.model.Contact;
import com.vit.vitapp.data.ContactRepository;
import com.vit.vitapp.data.remote.ApiService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;

public class ContactRemoteDataSource implements ContactRepository {

    @Inject
    ApiService apiService;

    @Inject
    public ContactRemoteDataSource() {
    }

    @Override
    public Flowable<List<Contact>> getContacts() {
        return apiService.getContacts(null).toFlowable();
    }

    @Override
    public Observable<List<Contact>> searchContacts(String keyword) {
        return apiService.getContacts(keyword).toObservable();
    }
}
