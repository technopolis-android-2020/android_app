package com.technopolis.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.technopolis.database.entity.News;
import com.technopolis.database.pojo.NewsWithAgent;

import java.util.List;

import io.reactivex.Observable;

@Dao
public interface NewsDao {

    @Query("SELECT MAX(publication_date) FROM news")
    Long getLatestDate();

    @Query("SELECT * FROM news")
    Observable<List<NewsWithAgent>> getAll();

    @Query("SELECT * FROM news ORDER BY publication_date")
    Observable<List<NewsWithAgent>> getAllSorterByDate();

    @Query("SELECT * FROM news WHERE publication_date = :publication_date")
    List<News> getNewsByPublicationDate(long publication_date);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<News> news);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNews(News news);

    @Query("DELETE FROM news")
    void deleteAll(/*List<News> news*/);

    @Query("DELETE FROM news WHERE news.title == :title")
    void deleteNewsByTitle(String title);
}
