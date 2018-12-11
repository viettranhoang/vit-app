package com.vit.vitapp.ui.contact;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.vit.vitapp.data.ContactRepository;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class ContactPresenter implements ContactContract.Presenter {

    @Inject
    ContactRepository contactRepository;

    @Inject
    ContactContract.View mContactsView;

    @NonNull
    private CompositeDisposable mCompositeDisposable;

    private PublishSubject<String> mPublishSubject;

    @Inject
    ContactPresenter() {
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void searchContacts(String keyword) {
        if (mPublishSubject == null) {
            mPublishSubject = PublishSubject.create();
            mCompositeDisposable.add(mPublishSubject
                    .debounce(300, TimeUnit.MILLISECONDS)
                    .map(text -> text.trim())
                    .distinctUntilChanged()
                    .filter(text -> !TextUtils.isEmpty(text))
                    .switchMap(text ->
                            contactRepository.searchContacts(text)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread()))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(contacts -> mContactsView.showContacts(contacts),
                            throwable -> mContactsView.showError(throwable))
            );
        }

        mPublishSubject.onNext(keyword);
    }

    @Override
    public void loadContacts() {
        mCompositeDisposable.add(contactRepository.getContacts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(contacts -> mContactsView.showContacts(contacts),
                        throwable -> mContactsView.showError(throwable))
        );
    }

    @Override
    public void subscribe() {
        loadContacts();
    }

    @Override
    public void unsubscribe() {
        mCompositeDisposable.clear();
    }
}
