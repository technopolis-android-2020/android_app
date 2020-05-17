package com.technopolis.network.retrofit;

import com.technopolis.network.model.NewsResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface NewsServerAPI {
    @GET("api/v1/news/getAll")
    Observable<List<NewsResponse>> getNews();
}
