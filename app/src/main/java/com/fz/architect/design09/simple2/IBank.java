package com.fz.architect.design09.simple2;

/**
 * 银行办理业务 - 目标接口（业务）
 * Created by fz on 2017/10/15.
 */

public interface IBank {
    /**
     * 申请办卡
     */
    public void applyBank();

    /**
     * 挂失
     */
    public void lostBank();

    /**
     * 额外业务
     */
    public void extraBank();
}
