package com.technopolis.network.retrofit;

import com.technopolis.network.model.NewsResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NewsServerAPI {
    @GET("api/v1/news/getAllNews")
    Observable<List<NewsResponse>> getNews();

    @GET("api/v1/news/fromDate/{date}")
    Observable<List<NewsResponse>> getNewsByDate(@Path("date") Long date);
}
