package com.fz.architect.design09;

/**
 * description: 静态代理设计模式 - 被代理对象
 * author: fz on 2017/10/11 12:51
 * email: 514905121@qq.com
 * version: 1.0
 */
public class Man implements IBank{
    @Override
    public void applyBank() {
        System.out.println("办卡");
    }
}
