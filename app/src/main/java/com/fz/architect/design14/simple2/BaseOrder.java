package com.fz.architect.design14.simple2;

import com.fz.architect.design14.simple2.status.OrderOperateStatus;

/**
 * Created by fz on 2017/11/4.
 */

public class BaseOrder {
    protected OrderOperateStatus mStatus;

    public void setStatus(OrderOperateStatus status){
        this.mStatus = status;
    }
}
