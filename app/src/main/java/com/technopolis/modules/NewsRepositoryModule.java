package com.technopolis.modules;

import android.content.Context;

import com.technopolis.database.repositories.NewsRepository;
import com.technopolis.scope.ApplicationScope;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module(includes = {ContextModule.class})
public class NewsRepositoryModule {

    @ApplicationScope
    @Provides
    NewsRepository provideNewsRepository(@Named("application_context") Context context) {
        return new NewsRepository(context);
    }
}
