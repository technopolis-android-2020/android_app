package com.technopolis.database.dao;

import com.technopolis.database.entity.News;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface NewsDao {

    @Query("SELECT * FROM news")
    List<News> getAll();

    @Query("SELECT * FROM news WHERE publication_date = :publication_date")
    List<News> getNewsByPublicationDate(long publication_date);

    @Insert
    void insertAll(News... news);

    @Insert
    void insertNews(News news);

    @Query("DELETE FROM news")
    void deleteAll(/*List<News> news*/);

    @Query("DELETE FROM news WHERE news.title == :title")
    void deleteNewsByTitle(String title);
}
