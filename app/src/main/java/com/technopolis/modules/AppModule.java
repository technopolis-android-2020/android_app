package com.technopolis.modules;

import android.content.Context;

import com.technopolis.database.repositories.NewsRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    NewsRepository provideNewsRepository(Context context){
        return new NewsRepository(context);
    }

    @Provides
    public Context context() {
        return context;
    }
}
