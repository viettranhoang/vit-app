package com.vit.data.features.contact;

import com.vit.data.features.contact.factory.ContactFactory;
import com.vit.data.features.contact.mapper.ContactEntityMapper;
import com.vit.data.features.contact.model.ContactEntity;
import com.vit.data.features.contact.source.ContactCache;
import com.vit.data.features.contact.source.ContactRemote;
import com.vit.domain.usecase.contact.model.Contact;
import com.vit.domain.usecase.contact.repository.ContactRepository;

import org.reactivestreams.Publisher;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class ContactRepositoryImpl implements ContactRepository {

    @Inject
    ContactFactory contactFactory;

    @Inject
    ContactEntityMapper contactEntityMapper;

    @Inject
    public ContactRepositoryImpl() {
    }

    @Override
    public Completable saveContacts(List<Contact> contacts) {
        List<ContactEntity> list = Observable.fromIterable(contacts)
                .map(contact -> contactEntityMapper.mapFromEntity(contact))
                .toList()
                .blockingGet();
        return contactFactory.retrieveCacheDataStore().saveContacts(list);
    }

    @Override
    public Flowable<List<Contact>> getContacts() {
        return contactFactory.retrieveCacheDataStore().isCached()
                .flatMapPublisher(isCache ->
                        contactFactory.retrieveDataStore(isCache)
                        .getContacts())
                .flatMap((Function<List<ContactEntity>, Publisher<List<Contact>>>) employeeEntities ->
                        Flowable.fromIterable(employeeEntities)
                                .map(employeeEntity -> contactEntityMapper.mapToEntity(employeeEntity))
                                .toList()
                                .toFlowable())
                .flatMap(contacts -> saveContacts(contacts)
                        .toSingle(() -> contacts)
                        .toFlowable());
    }
}
