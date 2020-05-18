package com.technopolis.database.repositories;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.technopolis.database.AppDatabase;
import com.technopolis.database.dao.NewsDao;
import com.technopolis.database.entity.News;
import com.technopolis.network.model.NewsResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

public class NewsRepository{

    private final MutableLiveData<List<News>> searchResults = new MutableLiveData<>();
    private LiveData<List<News>> allProducts;
    private NewsDao newsDao;
    private static final String LOG_TAG = NewsRepository.class.getSimpleName();

    public NewsRepository(final Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        newsDao = db.newsDao();
    }

    public void insertNews(final NewsResponse newsResponse) {
        News news = new News(newsResponse.id, newsResponse.title, newsResponse.logo,
                newsResponse.body, newsResponse.url, newsResponse.date, newsResponse.agent);

        Completable
                .complete()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        newsDao.insertNews(news);
                        Log.d(LOG_TAG, "News inserted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(LOG_TAG, "News insertion failed");
                    }
                });
    }

    public List<News> getAllNews() {
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
        Completable
                .complete()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        newsDao.insertAll(castToNews(newsResponses));
                        Log.d(LOG_TAG, "All news inserted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(LOG_TAG, "All news insertion failed");
                    }
                });
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
