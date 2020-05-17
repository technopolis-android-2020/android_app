package com.technopolis;

import android.app.Application;

import com.technopolis.components.AppComponent;
import com.technopolis.components.DaggerAppComponent;
import com.technopolis.modules.AppModule;

public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
