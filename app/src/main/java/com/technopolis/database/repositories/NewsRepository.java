package com.technopolis.database.repositories;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.technopolis.database.AppDatabase;
import com.technopolis.database.dao.AgentDao;
import com.technopolis.database.dao.NewsDao;
import com.technopolis.database.entity.Agent;
import com.technopolis.database.entity.News;

import java.util.List;

public class NewsRepository{

    private final MutableLiveData<List<News>> searchResults = new MutableLiveData<>();
    private LiveData<List<News>> allProducts;
    private NewsDao newsDao;
    private AgentDao agentDao;

    public NewsRepository(final Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        newsDao = db.newsDao();
        agentDao = db.agentDao();
        //allProducts = newsDao.getAll();
    }

    private void asyncFinished(final List<News> result){
        searchResults.setValue(result);
    }

    public void insertProduct(final News news) {
        InsertAsyncTask task = new InsertAsyncTask(newsDao, agentDao);
        task.execute(news);
    }
/*
    public void deleteProduct(final String name) {
        DeleteAsyncTask task = new DeleteAsyncTask(newsDao);
        task.execute(name);
    }
*/
    public void findProduct(final String name) {
        QueryAsyncTask task = new QueryAsyncTask(newsDao);
        task.delegate = this;
        task.execute(name);
    }

    //public LiveData<List<News>> getAllProducts() {
//        return allProducts;
//    }

    public List<News> getAllProducts() {
        return newsDao.getAll();
    }

    public MutableLiveData<List<News>> getSearchResults() {
        return searchResults;
    }

    private static class QueryAsyncTask extends AsyncTask<String, Void, List<News>>{

        private NewsDao asyncTaskDao;
        private NewsRepository delegate = null;

        public QueryAsyncTask(final NewsDao asyncTaskDao) {
            this.asyncTaskDao = asyncTaskDao;
        }

        @Override
        protected List<News> doInBackground(final String... strings) {
            return asyncTaskDao.getNewsByPublicationDate(Long.parseLong(strings[0]));
        }

        @Override
        protected void onPostExecute(List<News> result) {
            delegate.asyncFinished(result);
        }
    }

    private static class InsertAsyncTask extends AsyncTask<News, Void, Void> {

        private NewsDao asyncTaskDao;
        private AgentDao agentDao;

        InsertAsyncTask(NewsDao dao, AgentDao agentDao) {
            asyncTaskDao = dao;
            this.agentDao = agentDao;
        }

        @Override
        protected Void doInBackground(final News... params) {
            News news = params[0];
            Agent agent;
            if ( (agent = agentDao.getAgent(news.getAgentName())) != null) {
                news.setAgent_id(agent.id);
                asyncTaskDao.insertNews(news);
            }
            return null;
        }
    }
/*
    private static class DeleteAsyncTask extends AsyncTask<String, Void, Void> {

        private NewsDao asyncTaskDao;

        DeleteAsyncTask(NewsDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final String... params) {
            asyncTaskDao.deleteNewsByTitle(params[0]);
            return null;
        }
    }
 */
}
