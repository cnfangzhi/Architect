package com.fz.architect.day10;

import android.app.Application;

import com.fz.architect.day10.simple2.PreferencesUtils;

/**
 * Created by hcDarren on 2017/9/24.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        PreferencesUtils.getInstance().init(this);
    }
}
