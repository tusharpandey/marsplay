package com.app.assignment.view.list.adapter.paging;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.app.fragmentstack.dao.tables.AppPhotos;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppViewModel extends ViewModel {

    LiveData<PagedList<AppPhotos>> appLiveData;

    public AppViewModel() {
        init();
    }

    private void init() {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        AppDataFactory feedDataFactory = new AppDataFactory();

        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(10)
                        .setPageSize(20)
                        .build();

        LivePagedListBuilder liveVar = new LivePagedListBuilder(feedDataFactory, pagedListConfig);
        appLiveData = liveVar.setFetchExecutor(executor).build();
    }

    public LiveData<PagedList<AppPhotos>> getApplicationLiveData() {
        return appLiveData;
    }
}
