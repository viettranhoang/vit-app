package com.vit.domain.usecase.contact.repository;

import com.vit.domain.usecase.contact.model.Contact;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface ContactRepository {

    Completable saveContacts(List<Contact> contacts);

    Flowable<List<Contact>> getContacts();
}
