package com.vit.vitapp.di;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.support.annotation.NonNull;

import com.vit.cache.AppDatabase;
import com.vit.cache.DBConstants;
import com.vit.remote.VitService;
import com.vit.remote.VitServiceFactory;
import com.vit.vitapp.VitApplication;
import com.vit.vitapp.ui.contact.ContactActivity;
import com.vit.vitapp.ui.contact.module.ContactActivityModule;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


@Module(includes = {ActivityModule.class, RepositoryModule.class, ViewModelModule.class})
public abstract class AppModule {

    @Singleton
    @Binds
    abstract Application provideContext(VitApplication application);

    @Singleton
    @Provides
    static VitService vitService() {
        return new VitServiceFactory().makeApiService();
    }

    @Singleton
    @Provides
    @NonNull
    static AppDatabase appDatabase(VitApplication application) {
        return Room.databaseBuilder(application, AppDatabase.class, DBConstants.DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }

    @Singleton
    @Provides
    @Named("SchedulerType.IO")
    static Scheduler schedulerIO() {
        return Schedulers.io();
    }

    @Singleton
    @Provides
    @Named("SchedulerType.COMPUTATION")
    static Scheduler schedulerComputation() {
        return Schedulers.computation();
    }

    @Singleton
    @Provides
    @Named("SchedulerType.UI")
    static Scheduler schedulerUI() {
        return AndroidSchedulers.mainThread();
    }

}
