package com.vit.presentation.features.contact.model;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

import javax.inject.Inject;

public class ContactViewData {
    private String name;

    private String avatar;

    private String phone;

    private String email;

    public ContactViewData(String name, String avatar, String phone, String email) {
        this.name = name;
        this.avatar = avatar;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static class ContactDiffCallBack extends DiffUtil.ItemCallback<ContactViewData> {

        @Inject
        ContactDiffCallBack() {
        }

        @Override
        public boolean areItemsTheSame(@NonNull ContactViewData oldItem, @NonNull ContactViewData newItem) {
            return oldItem.phone.equals(newItem.phone);
        }

        @Override
        public boolean areContentsTheSame(@NonNull ContactViewData oldItem, @NonNull ContactViewData newItem) {
            return oldItem.email.equals(newItem.email);
        }
    }

}
