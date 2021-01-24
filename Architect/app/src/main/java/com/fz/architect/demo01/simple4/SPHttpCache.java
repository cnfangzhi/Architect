package com.fz.architect.demo01.simple4;

/**
 * Created by fz on 2017/8/26.
 */

public class SPHttpCache {
    public void saveCache(String finalUrl,String resultJson){
        PreferencesUtil.getInstance().saveParam(finalUrl,resultJson);
    }

    public String getCache(String finalUrl){
        return (String) PreferencesUtil.getInstance().getObject(finalUrl);
    }
}
