package com.vit.vitapp.ui.contact.module;

import android.app.Activity;

import com.vit.vitapp.di.scope.PerActivity;
import com.vit.vitapp.ui.base.module.BaseActivityModule;
import com.vit.vitapp.ui.contact.ContactActivity;
import com.vit.vitapp.ui.contact.listener.OnClickContactItemListener;

import dagger.Binds;
import dagger.Module;

@Module(includes = BaseActivityModule.class)
public abstract class ContactActivityModule {

    @Binds
    @PerActivity
    abstract Activity activity(ContactActivity contactActivity);

    @Binds
    abstract OnClickContactItemListener onClickContactItemListener(ContactActivity contactActivity);

}
