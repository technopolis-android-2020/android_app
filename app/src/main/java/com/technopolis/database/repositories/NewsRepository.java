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

/*
    public void deleteProduct(final String name) {
        DeleteAsyncTask task = new DeleteAsyncTask(newsDao);
        task.execute(name);
    }
*/

/*
    public MutableLiveData<List<News>> getSearchResults() {
        return searchResults;
    }
*/

/*
    private static class QueryAsyncTask extends AsyncTask<String, Void, List<News>>{
        private NewsDao asyncTaskDao;

        @Override
        protected List<News> doInBackground(final String... strings) {
            return asyncTaskDao.getNewsByPublicationDate(Long.parseLong(strings[0]));
        }
    }
*/

/*
    private static class DeleteAsyncTask extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(final String... params) {
            asyncTaskDao.deleteNewsByTitle(params[0]);
            return null;
        }
    }
 */
}
