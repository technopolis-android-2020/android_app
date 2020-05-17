package com.technopolis.network.retrofit;

import androidx.annotation.NonNull;

import com.technopolis.network.model.NewsResponse;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private NewsServerAPI newsServerAPI;

    public RetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://guarded-gorge-91889.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        newsServerAPI = retrofit.create(NewsServerAPI.class);
    }

    @NonNull
    public Observable<List<NewsResponse>> getNewsResponse() {
        return newsServerAPI.getNews();
    }
}
