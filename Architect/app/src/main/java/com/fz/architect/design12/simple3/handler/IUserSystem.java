package com.fz.architect.design12.simple3.handler;

/**
 * Created by fz on 2017/10/28.
 * 检验用户的处理接口
 */
public interface IUserSystem {
    /**
     * 根据用户名和密码查询用户信息
     * @param userName
     * @param userPwd
     * @return
     */
    public UserInfo queryUserInfo(String userName, String userPwd);
}
