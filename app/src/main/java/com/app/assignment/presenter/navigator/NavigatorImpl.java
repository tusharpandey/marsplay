package com.app.assignment.presenter.navigator;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import com.app.assignment.BuildConfig;
import com.app.assignment.view.list.AppImageListActivity;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NavigatorImpl implements Navigator {
    private Uri selectedImageUri;

    @Override
    public String navigateCamera(Activity activity) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {
            File photoFile = null;
            photoFile = createImageTempFile(activity);
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(activity, BuildConfig.APPLICATION_ID + ".provider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                activity.startActivityForResult(takePictureIntent, NavigationRequestCode.CAMERA);
                return photoFile.getAbsolutePath();
            }
        }
        return null;
    }

    public File createImageTempFile(Activity activity) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File file = null;
        try {
            file = File.createTempFile(imageFileName, ".jpg", activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    @Override
    public Uri cropImage(Activity activity, Uri sourceImage) {
        Uri destinationImage = Uri.fromFile(createImageTempFile(activity));
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

        intent.setType("image/*");

        List<ResolveInfo> list = activity.getPackageManager().queryIntentActivities(intent, 0);
        int size = list.size();
        if (size == 0) {
            selectedImageUri = sourceImage;
            intent.putExtra(MediaStore.EXTRA_OUTPUT, sourceImage);
            activity.startActivityForResult(intent, NavigationRequestCode.CROP);
            return selectedImageUri;
        } else {
            intent.setDataAndType(sourceImage, "image/*");
            intent.putExtra("crop", "true");
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            intent.putExtra("outputX", 300);
            intent.putExtra("outputY", 300);
            intent.putExtra("return-data", true);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, destinationImage);
            selectedImageUri = destinationImage;
            if (size == 1) {
                Intent i = new Intent(intent);
                ResolveInfo res = list.get(0);
                i.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
                activity.startActivityForResult(intent, NavigationRequestCode.CROP);
            } else {
                Intent i = new Intent(intent);
                i.putExtra(Intent.EXTRA_INITIAL_INTENTS, list.toArray(new Parcelable[list.size()]));
                activity.startActivityForResult(intent, NavigationRequestCode.CROP);
            }
        }
        return selectedImageUri;
    }

    @Override
    public void openList(Activity activity) {
        Intent intent = new Intent(activity, AppImageListActivity.class);
        activity.startActivity(intent);
    }
}
