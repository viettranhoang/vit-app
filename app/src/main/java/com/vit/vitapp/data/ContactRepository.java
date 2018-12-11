package com.vit.vitapp.data;

import com.vit.vitapp.data.model.Contact;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

public interface ContactRepository {

    Flowable<List<Contact>> getContacts();

    Observable<List<Contact>> searchContacts(String keyword);


}
