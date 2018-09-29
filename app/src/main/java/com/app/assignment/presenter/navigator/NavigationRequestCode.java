package com.app.assignment.presenter.navigator;


import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.app.assignment.presenter.navigator.NavigationRequestCode.CAMERA;

@Retention(RetentionPolicy.SOURCE)
@IntDef({CAMERA})
public @interface NavigationRequestCode {
    int CAMERA = 1;
    int CROP = 2;
}

