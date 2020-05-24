package com.technopolis.database;

import android.content.Context;
import android.util.Log;

import com.technopolis.database.dao.AgentDao;
import com.technopolis.database.dao.AgentWithNewsDao;
import com.technopolis.database.dao.NewsDao;
import com.technopolis.database.entity.Agent;
import com.technopolis.database.entity.News;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {News.class, Agent.class}, exportSchema = false, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static final String LOG_TAG = AppDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DB_NAME = "news_db";
    private static AppDatabase instance;

    public abstract NewsDao newsDao();
    public abstract AgentDao agentDao();
    public abstract AgentWithNewsDao agentWithNewsDao();

    public static synchronized AppDatabase getInstance(final Context context){
        if (instance == null){
            synchronized (LOCK) {
                Log.d(LOG_TAG, "Creating new database instance");
                instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DB_NAME)
                        .allowMainThreadQueries()
                        .build();
            }
        }
        Log.d(LOG_TAG, "Getting the database instance");
        return instance;
    }

    public static void destroyInstance(){
        instance = null;
    }
}
