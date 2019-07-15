package com.vit.cache.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.vit.cache.DBConstants;
import com.vit.cache.model.ContactCached;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;

@Dao
public abstract class ContactDao extends BaseDao<ContactCached> {

    @Query("SELECT * FROM " + DBConstants.Contact.TABLE_NAME)
    public abstract Maybe<List<ContactCached>> getContacts();

    @Query("SELECT * FROM " + DBConstants.Contact.TABLE_NAME +
            " WHERE " + DBConstants.Contact.COLUMN_NAME +" LIKE :keyword")
    public abstract Flowable<List<ContactCached>> searchContacts(String keyword);
}
