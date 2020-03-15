package com.fz.architect.demo01.simple4;

/**
 * Email 514905121@qq.com
 * Created by fz on 2017/3/5.
 * Version 1.0
 * Description:
 */
public abstract class HttpCallBack<T>{

    // 返回可以直接操作的对象
    public abstract void onSuccess(T result);

    public abstract void onFailure(Exception e);
}
