package com.app.assignment;

import android.app.Application;
import android.support.annotation.NonNull;

import com.app.assignment.model.di.ApplicationComponent;
import com.app.assignment.model.di.ApplicationModule;
import com.app.assignment.model.di.DaggerApplicationComponent;

public class MarsplayApplication extends Application {
    private ApplicationComponent applicationComponent;
    private static MarsplayApplication application;


    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
        getApplicationComponent().inject(this);
        application = this;
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }

    @NonNull
    public static MarsplayApplication get() {
        return application;
    }
}
