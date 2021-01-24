package com.fz.architect.design04.simple2;

import android.util.Log;

/**
 * Created by fz on 2017/9/30.
 */

public class PersonEat implements Eat {
    @Override
    public void eat() {
        Log.e("TAG","吃饭吃菜");
    }
}
