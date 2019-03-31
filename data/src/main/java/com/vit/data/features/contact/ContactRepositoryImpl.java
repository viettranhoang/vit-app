package com.vit.data.features.contact;

import com.vit.data.features.contact.mapper.ContactEntityMapper;
import com.vit.data.features.contact.model.ContactEntity;
import com.vit.data.features.contact.source.ContactCache;
import com.vit.data.features.contact.source.ContactRemote;
import com.vit.domain.model.Contact;
import com.vit.domain.repository.ContactRepository;


import org.reactivestreams.Publisher;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

@Singleton
public class ContactRepositoryImpl implements ContactRepository {

    @Inject
    ContactCache contactCache;

    @Inject
    ContactRemote contactRemote;

    @Inject
    ContactEntityMapper contactEntityMapper;

    @Override
    public Completable saveContacts(List<Contact> contacts) {
        List<ContactEntity> list = Observable.fromIterable(contacts)
                .map(contact -> contactEntityMapper.mapFromEntity(contact))
                .toList()
                .blockingGet();
        return contactCache.saveContact(list);
    }

    @Override
    public Flowable<List<Contact>> getContacts() {
        return receiveContacts()
                .flatMap((Function<List<ContactEntity>, Publisher<List<Contact>>>) employeeEntities ->
                        Flowable.fromIterable(employeeEntities)
                                .map(employeeEntity -> contactEntityMapper.mapToEntity(employeeEntity))
                                .toList()
                                .toFlowable())
                .flatMap(contacts -> saveContacts(contacts)
                                    .toSingle(() -> contacts)
                                    .toFlowable());
    }

    private Flowable<List<ContactEntity>> receiveContacts() {
        if (contactCache.isCache() && !contactCache.isExpired()) {
            return contactCache.getContacts();
        } else return contactRemote.getContacts();
    }
}
