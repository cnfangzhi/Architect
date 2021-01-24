package com.fz.architect.design03;

import android.app.Application;

import com.fz.architect.design03.simple2.PreferencesUtils;

/**
 * Created by fz on 2017/9/24.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        PreferencesUtils.getInstance().init(this);
    }
}
