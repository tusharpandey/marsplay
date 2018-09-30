package com.app.assignment.view.list.adapter.paging;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

public class AppDataFactory extends DataSource.Factory {

    private MutableLiveData<AppDataSource> mutableLiveData;
    private AppDataSource feedDataSource;

    public AppDataFactory() {
        this.mutableLiveData = new MutableLiveData<AppDataSource>();
    }

    @Override
    public DataSource create() {
        feedDataSource = new AppDataSource();
        mutableLiveData.postValue(feedDataSource);
        return feedDataSource;
    }

    public MutableLiveData<AppDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}
