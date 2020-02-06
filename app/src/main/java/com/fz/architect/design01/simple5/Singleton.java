package com.fz.architect.design01.simple5;

/**
 * 单例设计模式 - 自成一派
 * Created by fz on 2017/9/17.
 */

public class Singleton {
    private static Singleton mInstance;

    static {
        mInstance = new Singleton();
    }

    private Singleton() {

    }

    public static Singleton getInstance(){
        return mInstance;
    }
}
