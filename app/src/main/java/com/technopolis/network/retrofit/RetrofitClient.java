package com.technopolis.network.retrofit;

import androidx.annotation.NonNull;

import com.technopolis.network.model.NewsResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static NewsServerAPI newsServerAPI;

    public RetrofitClient() {
    }

    @NonNull
    public static Observable<List<NewsResponse>> getNewsResponse() {
        if (newsServerAPI == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://guarded-gorge-91889.herokuapp.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            newsServerAPI = retrofit.create(NewsServerAPI.class);
        }
        return newsServerAPI.getNews();
    }
}
