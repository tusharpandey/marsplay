package com.app.assignment.view.list.adapter.paging;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.app.assignment.MarsplayApplication;
import com.app.assignment.model.data.db.AppDao;
import com.app.fragmentstack.dao.tables.AppPhotos;

import java.util.List;

import javax.inject.Inject;

public class AppDataSource extends PageKeyedDataSource<Integer, AppPhotos>{

    int adjacentPageKey = 0;

    @Inject
    AppDao appDao;

    AppDataSource(){
        MarsplayApplication.get().getApplicationComponent().inject(this);
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, AppPhotos> callback) {
        List<AppPhotos> list = appDao.getPicturesNumber(0, params.requestedLoadSize);
        callback.onResult(list, null, list.size() + 1);
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, AppPhotos> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, AppPhotos> callback) {
        adjacentPageKey = adjacentPageKey + params.requestedLoadSize;
        List<AppPhotos> list = appDao.getPicturesNumber(params.key, adjacentPageKey);
        callback.onResult(list, adjacentPageKey);
    }
}
