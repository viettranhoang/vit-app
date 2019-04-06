package com.vit.presentation.features.contact.mapper;

import com.vit.domain.usecase.contact.model.Contact;
import com.vit.presentation.Mapper;
import com.vit.presentation.features.contact.model.ContactViewData;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ContactViewDataMapper implements Mapper<Contact, ContactViewData> {

    @Inject
    public ContactViewDataMapper() {
    }


    @Override
    public ContactViewData mapToViewData(Contact type) {
        if(type == null) {
            return null;
        }
        return new ContactViewData(type.getName(), type.getAvatar(), type.getPhone(), type.getEmail());
    }

    @Override
    public Contact mapFromViewData(ContactViewData type) {
        if(type == null) {
            return null;
        }
        return new Contact(type.getName(), type.getAvatar(), type.getPhone(), type.getEmail());
    }
}
