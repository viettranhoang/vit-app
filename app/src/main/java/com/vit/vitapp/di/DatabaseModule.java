package com.vit.vitapp.di;

import android.arch.persistence.room.Room;
import android.support.annotation.NonNull;

import com.vit.vitapp.VitApplication;
import com.vit.vitapp.data.cache.AppDatabase;
import com.vit.vitapp.data.cache.DBConstants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
abstract class DatabaseModule {

    @Singleton
    @Provides
    @NonNull
    static AppDatabase appDatabase(VitApplication application) {
        return Room.databaseBuilder(application, AppDatabase.class, DBConstants.DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }

}
