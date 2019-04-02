package com.vit.presentation.features.contact;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.vit.domain.model.Contact;
import com.vit.domain.usecase.contact_list.GetContactListUseCase;
import com.vit.presentation.SingleLiveEvent;
import com.vit.presentation.data.Resource;
import com.vit.presentation.data.ResourceState;
import com.vit.presentation.features.contact.mapper.ContactViewDataMapper;
import com.vit.presentation.features.contact.model.ContactViewData;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subscribers.DisposableSubscriber;

public class GetContactListViewModel extends ViewModel {

    @Inject
    GetContactListUseCase getContactListUseCase;

    @Inject
    ContactViewDataMapper contactViewDataMapper;

    private CompositeDisposable compositeDisposable;
    private SingleLiveEvent<Resource> contactListLiveData = new SingleLiveEvent<>();


    @Inject
    public GetContactListViewModel() {
        compositeDisposable = new CompositeDisposable();
    }

    public MutableLiveData<Resource> getContactList() {
        contactListLiveData.postValue(new Resource(ResourceState.LOADING, null, null));

        Disposable disposable = getContactListUseCase.execute(new DisposableSubscriber<List<Contact>>() {
            @Override
            public void onNext(List<Contact> contacts) {
                List<ContactViewData> list = Observable.fromIterable(contacts)
                        .map(contact -> contactViewDataMapper.mapToViewData(contact))
                        .toList()
                        .blockingGet();
                contactListLiveData.postValue(new Resource(ResourceState.SUCCESS, list, null));
            }

            @Override
            public void onError(Throwable t) {
                contactListLiveData.postValue(new Resource<>(ResourceState.ERROR, null, t));
            }

            @Override
            public void onComplete() {

            }
        });

        compositeDisposable.add(disposable);

        return contactListLiveData;
    }

    @Override
    protected void onCleared() {
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
        super.onCleared();
    }
}
