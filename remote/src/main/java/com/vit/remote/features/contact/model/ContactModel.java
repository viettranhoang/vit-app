package com.vit.remote.features.contact.model;

import com.google.gson.annotations.SerializedName;

public class ContactModel {
    private String name;

    @SerializedName("image")
    private String avatar;

    private String phone;

    private String email;

    public ContactModel(String name, String avatar, String phone, String email) {
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
}
