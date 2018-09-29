package com.app.assignment.presenter.upload;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;

import com.app.assignment.presenter.BasePresenter;
import com.app.assignment.presenter.navigator.Navigator;

import javax.inject.Inject;

public class UploadPresenter implements BasePresenter {

    @Inject
    Navigator navigator;
    private View view;
    private Bitmap bitmap;

    @Inject
    UploadPresenter() {
    }

    public String openCamera() {
        return navigator.navigateCamera((Activity) view.getContext());
    }

    public Uri cropImage(Uri imageUri) {
        return navigator.cropImage((Activity) view.getContext(), imageUri);
    }

    @Override
    public void onAttachedToWindow(View view) {
        this.view = view;
    }
}
