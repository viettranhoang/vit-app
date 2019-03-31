package com.vit.remote.features.contact.mapper;

import com.vit.data.features.contact.model.ContactEntity;
import com.vit.remote.common.Mapper;
import com.vit.remote.features.contact.model.ContactModel;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ContactModelMapper implements Mapper<ContactModel, ContactEntity> {

    @Inject
    public ContactModelMapper() {
    }

    @Override
    public ContactEntity mapToEntity(ContactModel type) {
        if (type == null) {
            return null;
        }
        return new ContactEntity(type.getName(), type.getAvatar(), type.getPhone(), type.getEmail());
    }

    @Override
    public ContactModel mapToModel(ContactEntity type) {
        if (type == null) {
            return null;
        }
        return new ContactModel(type.getName(), type.getAvatar(), type.getPhone(), type.getEmail());
    }
}
