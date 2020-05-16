package com.technopolis.modules;

import android.app.Activity;
import android.content.Context;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    private final Context context;

    public ContextModule(Activity context) {
        this.context = context;
    }

    @Named("application_context")
    @Provides
    public Context context() {
        return context.getApplicationContext();
    }

}
