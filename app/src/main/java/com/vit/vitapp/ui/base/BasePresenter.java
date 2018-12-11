package com.vit.vitapp.ui.base;

public interface BasePresenter<T> {

    void takeView(T view);

    void subscribe();

    void unsubscribe();

}