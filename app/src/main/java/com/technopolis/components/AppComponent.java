package com.technopolis.components;

import com.technopolis.activity.MainActivity;
import com.technopolis.fragments.FullNewsFragment;
import com.technopolis.fragments.SettingsFragment;
import com.technopolis.fragments.StoriesFragment;
import com.technopolis.modules.AppModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(MainActivity activity);

    void injectSettingsFragment(SettingsFragment fragment);

    void injectStoriesFragment(StoriesFragment fragment);

    void injectFullNewsFragment(FullNewsFragment newsFragment);
}
