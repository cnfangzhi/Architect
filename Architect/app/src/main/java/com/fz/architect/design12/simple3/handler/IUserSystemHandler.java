package com.fz.architect.design12.simple3.handler;

/**
 * Created by fz on 2017/10/28.
 * 责任链设计模式 - 抽象处理者接口
 */
public interface IUserSystemHandler<T extends IUserSystemHandler> {
    public void nextHandler(T systemHandler);
}
