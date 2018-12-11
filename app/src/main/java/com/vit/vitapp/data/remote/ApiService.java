package com.vit.vitapp.data.remote;

import com.vit.vitapp.data.model.Contact;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    String URL_SERVER = "https://api.androidhive.info/json/";

    @GET("contacts.php")
    Single<List<Contact>> getContacts(@Query("search") String query);
}
