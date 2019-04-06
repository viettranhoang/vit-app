package com.vit.vitapp.di;

import com.vit.vitapp.VitApplication;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class})
public interface AppComponent extends AndroidInjector<VitApplication> {
    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<VitApplication> {
    }
}
