package com.technopolis.database;

import com.technopolis.database.dao.NewsDao;
import com.technopolis.database.entity.News;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {News.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NewsDao newsDao();
}
