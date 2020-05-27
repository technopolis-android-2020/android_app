package com.technopolis.database.repositories;

import android.content.Context;

import com.technopolis.database.AppDatabase;
import com.technopolis.database.dao.AgentDao;
import com.technopolis.database.dao.AgentWithNewsDao;
import com.technopolis.database.dao.NewsDao;
import com.technopolis.database.entity.Agent;
import com.technopolis.database.entity.News;
import com.technopolis.database.pojo.AgentWithNews;
import com.technopolis.database.pojo.NewsWithAgent;
import com.technopolis.network.model.NewsResponse;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import io.reactivex.Observable;

public class NewsRepository {

    private NewsDao newsDao;
    private AgentDao agentDao;
    private AgentWithNewsDao agentWithNewsDao;

    public NewsRepository(final Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        newsDao = db.newsDao();
        agentDao = db.agentDao();
        agentWithNewsDao = db.agentWithNewsDao();
    }

    public Long getLatestDate() {
        return newsDao.getLatestDate();
    }

    private News convertNews(final NewsResponse newsResponse) {
        Agent agent;
        News news = new News(newsResponse.id, newsResponse.title, newsResponse.logo,
                newsResponse.body, newsResponse.url, newsResponse.date, newsResponse.agent);

        if ((agent = agentDao.getAgent(news.getAgentName())) != null) {
            news.setAgentId(agent.id);
        }

        return news;
    }

    public void insertNews(final NewsResponse newsResponse) {
        newsDao.insertNews(convertNews(newsResponse));
    }

    public Observable<List<NewsWithAgent>> getAllNews() {
        return newsDao.getAll();
    }

    private List<News> castToNews(final List<NewsResponse> newsResponses) {
        List<News> news = new ArrayList<>();
        for (NewsResponse response : newsResponses
        ) {
            news.add(convertNews(response));
        }
        return news;
    }

    public void insertAllNews(final List<NewsResponse> newsResponses) {
        newsDao.insertAll(castToNews(newsResponses));
    }


    public NewsWithAgent loadOneLastNews(@NonNull final String agentName) {
        AgentWithNews agentWithNews= agentWithNewsDao.loadAgentWithNews(agentName);
        NewsWithAgent result = new NewsWithAgent();

        if (agentWithNews == null) {
            return null;
        }

        // find one latest news
        News latestNews = agentWithNews.news.get(0);
        for (News news: agentWithNews.news) {
            if (news.getPublicationDate().compareTo(latestNews.getPublicationDate()) > 0) {
                latestNews = news;
            }
        }
        latestNews.setAgentName(agentName);

        // set result
        result.news = latestNews;
        result.agent = agentWithNews.agent;

        return result;
    }

}
