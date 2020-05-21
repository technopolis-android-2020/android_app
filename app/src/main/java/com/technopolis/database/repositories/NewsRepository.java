package com.technopolis.database.repositories;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.technopolis.database.AppDatabase;
import com.technopolis.database.dao.NewsDao;
import com.technopolis.database.entity.News;
import com.technopolis.network.model.NewsResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class NewsRepository{

    private final MutableLiveData<List<News>> searchResults = new MutableLiveData<>();
    private LiveData<List<News>> allProducts;
    private NewsDao newsDao;

    public NewsRepository(final Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        newsDao = db.newsDao();
    }

    private News convertNews(final NewsResponse newsResponse){
        return new News(newsResponse.id, newsResponse.title, newsResponse.logo,
                newsResponse.body, newsResponse.url, newsResponse.date, newsResponse.agent);
    }

    public void insertNews(final NewsResponse newsResponse) {
        newsDao.insertNews(convertNews(newsResponse));
    }

    public Observable<List<News>> getAllNews() {
        return newsDao.getAll();
    }

    private List<News> castToNews(final List<NewsResponse> newsResponses){
        List<News> news = new ArrayList<>();
        for (NewsResponse response:newsResponses
             ) {
            news.add(new News(response.id, response.title, response.logo, response.body,
                    response.url, response.date, response.agent));
        }
        return news;
    }

    public void insertAllNews(final List<NewsResponse> newsResponses) {
        newsDao.insertAll(castToNews(newsResponses));
    }

}
