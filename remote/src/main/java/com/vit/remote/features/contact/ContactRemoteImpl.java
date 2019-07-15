package com.vit.remote.features.contact;

import com.vit.data.features.contact.model.ContactEntity;
import com.vit.data.features.contact.source.ContactRemote;
import com.vit.remote.VitService;
import com.vit.remote.features.contact.mapper.ContactModelMapper;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;

@Singleton
public class ContactRemoteImpl implements ContactRemote {

    @Inject
    VitService vitService;

    @Inject
    ContactModelMapper mapper;

    @Inject
    public ContactRemoteImpl() {
    }

    @Override
    public Flowable<List<ContactEntity>> getContacts() {
        return vitService.getContacts(null)
                .flatMapPublisher(contactModels -> Flowable.fromIterable(contactModels)
                        .map(contactModel -> mapper.mapToEntity(contactModel))
                        .toList()
                        .toFlowable());
    }
}
