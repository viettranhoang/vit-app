package com.vit.remote;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.inject.Inject;
import javax.inject.Named;

import static com.vit.remote.VitService.URL_SERVER;

public class VitServiceFactory {

    private Interceptor interceptor;

    @Inject
    public VitServiceFactory(@Named("MockInterceptor") Interceptor interceptor){
        this.interceptor = interceptor;
    }

    public VitService makeApiService() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL_SERVER)
                .client(makeOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(VitService.class);
    }

    private OkHttpClient makeOkHttpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(chain -> {
                    Request request = chain.request()
                            .newBuilder()
                            .addHeader("Content-Type", "application/json")
                            .build();

                    return chain.proceed(request);
                })
                .addInterceptor(interceptor)
                .build();
    }
}
