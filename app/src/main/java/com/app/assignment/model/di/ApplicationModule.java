package com.app.assignment.model.di;

import com.app.assignment.MarsplayApplication;
import com.app.assignment.presenter.navigator.Navigator;
import com.app.assignment.presenter.navigator.NavigatorImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private final MarsplayApplication application;

    public ApplicationModule(MarsplayApplication application) {
        this.application = application;
    }

    @Provides
    public Navigator provideNavigatiorImpl(){
        return new NavigatorImpl();
    }
}
