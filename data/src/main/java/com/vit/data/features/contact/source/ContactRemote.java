package com.vit.data.features.contact.source;

import com.vit.data.features.contact.model.ContactEntity;

import java.util.List;

import io.reactivex.Flowable;

public interface ContactRemote {

    Flowable<List<ContactEntity>> getContacts();
}
