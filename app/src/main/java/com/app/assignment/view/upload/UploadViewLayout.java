package com.app.assignment.view.upload;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.FileProvider;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.app.assignment.BuildConfig;
import com.app.assignment.MarsplayApplication;
import com.app.assignment.R;
import com.app.assignment.presenter.navigator.NavigationRequestCode;
import com.app.assignment.presenter.upload.UploadPresenter;
import com.app.assignment.view.UploadView;
import com.bumptech.glide.Glide;

import java.io.File;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UploadViewLayout extends ConstraintLayout implements UploadView {

    @Inject
    UploadPresenter presenter;

    @BindView(R.id.image)
    ImageView imageView;
    private String cameraUri;
    private Uri cropUri;

    public UploadViewLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeView();
    }

    private void initializeView() {
        inflate(getContext(), R.layout.layout_upload, this);
        MarsplayApplication.get().getApplicationComponent().inject(this);
        ButterKnife.bind(this);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        presenter.onAttachedToWindow(this);
        cameraUri = presenter.openCamera();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override
    public void onOptionsItemSelected(int resId) {
        switch (resId) {
            case R.id.upload:
                presenter.uploadImage(cropUri + "");
                break;
            case R.id.list:
                presenter.openList();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case NavigationRequestCode.CAMERA:
                    Uri photoURI = FileProvider.getUriForFile(getContext(), BuildConfig.APPLICATION_ID + ".provider", new File(cameraUri));
                    cropUri = presenter.cropImage(photoURI);
                    break;
                case NavigationRequestCode.CROP:
                    Glide.with(this).load(cropUri).into(imageView);
                    break;

            }
        } else {
            ((Activity) getContext()).finish();
        }
    }

}
