package com.app.assignment.presenter.upload;

import android.app.Activity;
import android.net.Uri;
import android.view.View;

import com.app.assignment.model.data.db.AppDao;
import com.app.assignment.model.data.db.TaskScheduler;
import com.app.assignment.presenter.BasePresenter;
import com.app.assignment.presenter.navigator.Navigator;
import com.app.fragmentstack.dao.tables.AppPhotos;

import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

public class UploadPresenter implements BasePresenter {

    @Inject
    Navigator navigator;

    @Inject
    AppDao appDao;

    @Inject
    TaskScheduler scheduler;

    private View view;

    @Inject
    UploadPresenter() {
    }

    public String openCamera() {
        return navigator.navigateCamera((Activity) view.getContext());
    }

    public Uri cropImage(Uri imageUri) {
        return navigator.cropImage((Activity) view.getContext(), imageUri);
    }

    public void uploadImage(final String filePath) {
        scheduler.makeItBackground(new Callable() {
            @Override
            public Object call() {
                AppPhotos appPhotos = new AppPhotos(appDao.getPicturesCount() + 1, filePath);
                appDao.insertPicture(appPhotos);
                return 1;
            }
        }).subscribe(new Consumer() {
            @Override
            public void accept(Object o) {
                openList();
            }
        });
    }

    @Override
    public void onAttachedToWindow(View view) {
        this.view = view;
    }

    public void openList() {
        navigator.openList((Activity) view.getContext());
    }
}
