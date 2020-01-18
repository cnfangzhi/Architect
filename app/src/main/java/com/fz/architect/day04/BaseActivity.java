package com.fz.architect.day04;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by hcDarren on 2017/9/4.
 */

public class BaseActivity extends AppCompatActivity{

    // 上限
    public void startActivity(Class<? extends BaseActivity> clazz) {
        Intent intent = new Intent(this,clazz);
        startActivity(intent);
    }

    // 下限 包括 MainActivity 和它的父类
    public void startActivity1(Class<? super BaseActivity> clazz) {
        Intent intent = new Intent(this,clazz);
        startActivity(intent);
    }
}
