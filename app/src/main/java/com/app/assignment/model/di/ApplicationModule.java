package com.app.assignment.model.di;

import android.arch.persistence.room.Room;

import com.app.assignment.MarsplayApplication;
import com.app.assignment.model.data.db.AppDao;
import com.app.assignment.model.data.db.AppDatabase;
import com.app.assignment.model.data.db.TaskScheduler;
import com.app.assignment.presenter.navigator.Navigator;
import com.app.assignment.presenter.navigator.NavigatorImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private final MarsplayApplication application;

    public ApplicationModule(MarsplayApplication application) {
        this.application = application;
        buildDb();
    }

    @Provides
    public Navigator provideNavigatiorImpl() {
        return new NavigatorImpl();
    }

    public AppDatabase buildDb() {
        return Room.databaseBuilder(application, AppDatabase.class, "database-name").build();
    }

    @Singleton
    @Provides
    public AppDao provideDao() {
        return buildDb().appDao();
    }

    @Singleton
    @Provides
    public TaskScheduler provideTaskScheduler() {
        return new TaskScheduler();
    }

}
