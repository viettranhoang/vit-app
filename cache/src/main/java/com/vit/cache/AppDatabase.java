package com.vit.cache;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.vit.cache.dao.ContactDao;
import com.vit.cache.model.ContactCached;


@Database(entities = ContactCached.class, version = DBConstants.DATABASE_VERSION, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ContactDao contactDao();

}
