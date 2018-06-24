package com.infosys.androidbarchartassignment;

import android.app.Application;
import android.content.Context;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class App extends Application {

    private static Context context;
    private static Cache cache;

    // Get application context
    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        int cacheSize = 10 * 1024 * 1024; // 10 MB
        cache = new Cache(getCacheDir(), cacheSize);
    }

    /**
     * Build Retrofit Client
     * @param url base url
     * @return retrofit
     */
    public static Retrofit getClient(String url){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                .build();
        return new Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
