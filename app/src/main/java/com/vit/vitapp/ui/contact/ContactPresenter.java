package com.vit.vitapp.ui.contact;

import android.support.annotation.NonNull;

import com.vit.vitapp.data.ContactRepository;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ContactPresenter implements ContactContract.Presenter {

    @Inject
    ContactRepository contactRepository;

    @Inject
    ContactContract.View mContactsView;

    @NonNull
    private CompositeDisposable mCompositeDisposable;

    @Inject
    ContactPresenter() {
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void searchContacts(String keyword) {

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
    public void takeView(ContactContract.View view) {
        mContactsView = view;
        loadContacts();
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
