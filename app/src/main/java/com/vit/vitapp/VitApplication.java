package com.vit.vitapp;


import com.vit.vitapp.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class VitApplication extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends VitApplication> applicationInjector() {
        return DaggerAppComponent.builder().create(this);
    }
}