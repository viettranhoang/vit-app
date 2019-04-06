package com.vit.cache.source;

import com.vit.cache.AppDatabase;
import com.vit.cache.PrefUtils;
import com.vit.cache.mapper.ContactCacheMapper;
import com.vit.data.features.contact.model.ContactEntity;
import com.vit.data.features.contact.source.ContactCache;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.Single;

public class ContactCacheImpl implements ContactCache {

    public static final String TAG = ContactCacheImpl.class.getSimpleName();

    private final long EXPIRATION_TIME = 60 * 10 * 1000;


    @Inject
    AppDatabase database;

    @Inject
    ContactCacheMapper mapper;

    @Inject
    PrefUtils prefUtils;

    @Inject
    public ContactCacheImpl() {
    }

    @Inject
    @Named("SchedulerType.COMPUTATION")
    Scheduler schedulerComputation;

    @Override
    public Completable saveContact(List<ContactEntity> contacts) {
        return Observable.fromIterable(contacts)
                .map(contactEntity -> mapper.mapToCached(contactEntity))
                .toList()
                .toFlowable()
                .flatMapCompletable(contactCacheds -> {
                    database.contactDao().insert(contactCacheds);
                    return Completable.complete();
                })
                .subscribeOn(schedulerComputation);

    }

    @Override
    public Flowable<List<ContactEntity>> getContacts() {
        return database.contactDao().getContacts()
                .flatMapPublisher(contactCacheds -> Flowable.fromIterable(contactCacheds)
                        .map(contactCached -> mapper.mapFromCached(contactCached))
                        .toList()
                        .toFlowable())
                .subscribeOn(schedulerComputation);
    }

    @Override
    public Single<Boolean> isCache() {
        return Single.defer(() -> Single.just(!database.contactDao().getContacts().isEmpty().blockingGet())
        .subscribeOn(schedulerComputation));

    }

    @Override
    public boolean isExpired() {
        long currentTime = System.currentTimeMillis();
        long lastCacheUpdateTime = prefUtils.get(PrefUtils.PREF_KEY.PREF_KEY_LAST_CACHE, 0L);
        return currentTime - lastCacheUpdateTime > EXPIRATION_TIME;
    }

    @Override
    public void setLastCacheTime(long lastCacheTime) {
        prefUtils.set(PrefUtils.PREF_KEY.PREF_KEY_LAST_CACHE, lastCacheTime);
    }
}
