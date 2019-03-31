package com.vit.data.features.contact.model;

public class ContactEntity {

    private String name;

    private String avatar;

    private String phone;

    private String email;

    public ContactEntity(String name, String avatar, String phone, String email) {
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
