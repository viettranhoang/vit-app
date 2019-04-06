package com.vit.vitapp.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;


import com.vit.presentation.features.VitViewModelFactory;
import com.vit.presentation.features.contact.GetContactListViewModel;
import com.vit.vitapp.di.key.ViewModelKey;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(VitViewModelFactory vitViewModelFactory);

    @Binds
    @IntoMap
    @ViewModelKey(GetContactListViewModel.class)
    abstract ViewModel bindContactListViewModel(GetContactListViewModel getContactListViewModel);

}
