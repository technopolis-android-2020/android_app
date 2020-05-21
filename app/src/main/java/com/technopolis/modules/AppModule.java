package com.technopolis.modules;

import android.content.Context;

import com.technopolis.database.repositories.AgentRepository;
import com.technopolis.database.repositories.AgentWithNewsRepository;
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
    @Singleton
    AgentRepository provideAgentRepository(Context context) {
        return new AgentRepository(context);
    }

    @Provides
    @Singleton
    AgentWithNewsRepository provideAgentWithNewsRepository(Context context) {
        return new AgentWithNewsRepository(context);
    }

    @Provides
    public Context context() {
        return context;
    }
}
