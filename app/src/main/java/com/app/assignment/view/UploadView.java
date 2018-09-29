package com.app.assignment.view;

import android.content.Intent;
import android.support.annotation.Nullable;

public interface UploadView {
    void onOptionsItemSelected(int resId);

    void onActivityResult(int requestCode, int resultCode, @Nullable Intent data);
}
