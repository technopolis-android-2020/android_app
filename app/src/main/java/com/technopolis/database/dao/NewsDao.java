package com.technopolis.database.dao;

import com.technopolis.database.entity.News;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import io.reactivex.Observable;

@Dao
public interface NewsDao {

    @Query("SELECT * FROM news")
    Observable<List<News>> getAll();

    @Query("SELECT * FROM news WHERE publication_date = :publication_date")
    List<News> getNewsByPublicationDate(long publication_date);

    @Insert
    void insertAll(List<News> news);

    @Insert
    void insertNews(News news);

    @Query("DELETE FROM news")
    void deleteAll(/*List<News> news*/);

    @Query("DELETE FROM news WHERE news.title == :title")
    void deleteNewsByTitle(String title);
}
