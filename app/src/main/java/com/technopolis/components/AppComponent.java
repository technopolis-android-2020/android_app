package com.technopolis.components;

import com.technopolis.activity.MainActivity;
import com.technopolis.modules.AppModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(MainActivity activity);
}
