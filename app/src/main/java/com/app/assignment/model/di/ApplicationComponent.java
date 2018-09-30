package com.app.assignment.model.di;

import com.app.assignment.MarsplayApplication;
import com.app.assignment.view.list.AppImageListLayout;
import com.app.assignment.view.list.adapter.paging.AppDataSource;
import com.app.assignment.view.upload.UploadViewLayout;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class})
public interface ApplicationComponent {

    void inject(MarsplayApplication marsplayApplication);

    void inject(UploadViewLayout uploadViewLayout);

    void inject(AppDataSource appDataSource);

    void inject(AppImageListLayout appImageListLayout);
}
