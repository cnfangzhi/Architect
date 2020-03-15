package com.fz.architect.design14.simple2;

import com.fz.architect.design14.simple2.status.ObligationStatus;
import com.fz.architect.design14.simple2.status.OrderOperateStatus;
import com.fz.architect.design14.simple2.status.PaidStatus;
import com.fz.architect.design14.simple2.status.WaitRecevingStatus;

/**
 * Created by fz on 2017/11/4.
 */

public class Order extends BaseOrder implements OrderOperateStatus {

    public Order(){
        // 默认的状态
        mStatus = new ObligationStatus();
    }

    @Override
    public void pay() {
        mStatus.pay();
        // 状态设置已支付的状态
        setStatus(new PaidStatus());
    }

    @Override
    public void deliverGoods() {
        mStatus.deliverGoods();
        // 代发货的状态
        setStatus(new WaitRecevingStatus());
    }
}
