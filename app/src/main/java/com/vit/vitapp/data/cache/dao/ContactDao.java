package com.vit.vitapp.data.cache.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.vit.vitapp.data.cache.DBConstants;
import com.vit.vitapp.data.model.Contact;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public abstract class ContactDao extends BaseDao<Contact> {

    @Query("SELECT * FROM " + DBConstants.Contact.TABLE_NAME)
    public abstract Flowable<List<Contact>> getContacts();

    @Query("SELECT * FROM " + DBConstants.Contact.TABLE_NAME +
            " WHERE " + DBConstants.Contact.COLUMN_NAME +" LIKE :keyword")
    public abstract Flowable<List<Contact>> searchContacts(String keyword);
}
