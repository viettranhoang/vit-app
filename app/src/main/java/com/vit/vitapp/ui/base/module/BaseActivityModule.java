package com.vit.vitapp.ui.base.module;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;

import com.vit.vitapp.di.scope.PerActivity;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class BaseActivityModule {

    @Binds
    @PerActivity
    abstract Context activityContext(Activity activity);

}
