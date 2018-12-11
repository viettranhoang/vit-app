package com.vit.vitapp.ui.contact.module;

import com.vit.vitapp.di.scope.PerActivity;
import com.vit.vitapp.ui.contact.ContactActivity;
import com.vit.vitapp.ui.contact.ContactContract;
import com.vit.vitapp.ui.contact.ContactPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class ContactActivityModule {


    @Binds
    abstract ContactContract.Presenter contactPresenter(ContactPresenter presenter);

    @Binds
    abstract ContactContract.View contactView(ContactActivity activity);

}
