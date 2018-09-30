package com.app.assignment.presenter.navigator;

import android.app.Activity;
import android.net.Uri;

public interface Navigator {
    String navigateCamera(Activity activity);

    Uri cropImage(Activity activity, Uri imageUri);

    void openList(Activity activity);
}
