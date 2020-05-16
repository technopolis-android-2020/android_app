package com.technopolis.components;

import com.technopolis.modules.NewsRepositoryModule;
import com.technopolis.database.repositories.NewsRepository;
import com.technopolis.scope.ApplicationScope;

import dagger.Component;

@ApplicationScope
@Component(modules = {NewsRepositoryModule.class})
public interface DatabaseComponent {
    NewsRepository getNewsRepository();
}
