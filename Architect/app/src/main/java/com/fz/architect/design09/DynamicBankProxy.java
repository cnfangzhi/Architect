package com.fz.architect.design09;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * description: 动态代理设计模式 - InvocationHandler
 * author: fz on 2017/10/11 13:59
 * email: 514905121@qq.com
 * version: 1.0
 */
public class DynamicBankProxy implements InvocationHandler{
    private Object object;

    public DynamicBankProxy(Object object){
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("数据统计");
        Object result = method.invoke(object,args);
        System.out.println("完毕");
        return result;
    }
}
