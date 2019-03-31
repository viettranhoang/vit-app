package com.vit.domain.repository;

import com.vit.domain.model.Contact;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface ContactRepository {

    Completable saveContacts(List<Contact> contacts);

    Flowable<List<Contact>> getContacts();
}
