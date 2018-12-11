package com.vit.vitapp.ui.contact;

import android.support.annotation.NonNull;
import android.util.Log;

import com.vit.vitapp.data.model.Contact;
import com.vit.vitapp.data.remote.ApiService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ContactPresenter implements ContactContract.Presenter {

    @Inject
    ApiService apiService;

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
        mCompositeDisposable.add(apiService.getContacts(null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Contact>>() {

                    @Override
                    public void onSuccess(List<Contact> contacts) {
                        mContactsView.showContacts(contacts);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mContactsView.showError(e);
                    }
                })
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
