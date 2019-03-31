package com.vit.cache.mapper;

import com.vit.cache.model.ContactCached;
import com.vit.data.features.contact.model.ContactEntity;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ContactCacheMapper implements Mapper<ContactCached, ContactEntity>{

    @Inject
    public ContactCacheMapper() {
    }

    @Override
    public ContactEntity mapFromCached(ContactCached type) {
        if (type == null) {
            return null;
        }
        return new ContactEntity(type.getName(), type.getProfileImage(), type.getPhone(), type.getEmail());
    }

    @Override
    public ContactCached mapToCached(ContactEntity type) {
        if (type == null) {
            return null;
        }
        return new ContactCached(type.getName(), type.getAvatar(), type.getPhone(), type.getEmail());
    }
}
