package com.vit.remote;

import com.vit.remote.features.contact.model.ContactModel;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VitService {

    String URL_SERVER = "https://api.androidhive.info/json/";

    @GET("contacts.php")
    Single<List<ContactModel>> getContacts(@Query("search") String query);
}
