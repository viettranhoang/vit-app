package com.vit.data.features.contact.mapper;

import com.vit.data.Mapper;
import com.vit.data.features.contact.model.ContactEntity;
import com.vit.domain.model.Contact;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ContactEntityMapper implements Mapper<ContactEntity, Contact> {

    @Inject
    public ContactEntityMapper() {
    }

    @Override
    public Contact mapToEntity(ContactEntity type) {
        if (type == null) {
            return null;
        }
        return new Contact(type.getName(), type.getAvatar(), type.getPhone(), type.getEmail());
    }

    @Override
    public ContactEntity mapFromEntity(Contact type) {
        if (type == null) {
            return null;
        }
        return new ContactEntity(type.getName(), type.getAvatar(), type.getPhone(), type.getEmail());
    }
}
