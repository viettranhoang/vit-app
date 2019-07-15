package com.vit.vitapp.di;

import com.vit.vitapp.ui.contact.ContactActivity;
import com.vit.vitapp.ui.contact.module.ContactActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector(modules = ContactActivityModule.class)
    abstract ContactActivity contactActivityInjector();
}
