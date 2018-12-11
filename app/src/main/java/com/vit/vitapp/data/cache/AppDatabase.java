package com.vit.vitapp.data.cache;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.vit.vitapp.data.cache.dao.ContactDao;
import com.vit.vitapp.data.model.Contact;


@Database(entities = Contact.class, version = DBConstants.DATABASE_VERSION, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ContactDao contactDao();

}
