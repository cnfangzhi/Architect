package com.fz.architect.design12.simple3.facade;


import com.fz.architect.design12.simple3.NYUserSystem;
import com.fz.architect.design12.simple3.QQUserSystem;
import com.fz.architect.design12.simple3.WXUserSystem;
import com.fz.architect.design12.simple3.handler.AbsUserSystemHandler;
import com.fz.architect.design12.simple3.handler.IUserSystem;
import com.fz.architect.design12.simple3.handler.UserInfo;

/**
 * Created by fz on 2017/10/22.
 * 外观设计模式 - 易于使用的高层次
 */
public class UserSystemFacade implements IUserSystem {
    // 第一应该检查的系统
    private AbsUserSystemHandler mFirstSystemHandler;

    public UserSystemFacade(){
        // 根据用户名和密码去查询用户信息，
        // 如果没有查询到那么代表登录失败，如果查询到了代表登录成功
        mFirstSystemHandler = new WXUserSystem();
        QQUserSystem qqUserSystem = new QQUserSystem();
        NYUserSystem nyUserSystem = new NYUserSystem();

        mFirstSystemHandler.nextHandler(qqUserSystem);
        qqUserSystem.nextHandler(nyUserSystem);
    }
    @Override
    public UserInfo queryUserInfo(String userName, String userPwd) {
        return mFirstSystemHandler.queryUserInfo(userName,userPwd);
    }
}
