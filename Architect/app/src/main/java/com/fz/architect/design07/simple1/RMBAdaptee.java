package com.fz.architect.design07.simple1;

/**
 * Created by fz on 2017/10/8.
 * 代表第一个版本的人民币
 */
public class RMBAdaptee {
    private float mRmb;

    public RMBAdaptee(float rmb){
        this.mRmb = rmb;
    }

    /**
     * 获取人民币
     * @return
     */
    public float getRmb() {
        return mRmb;
    }

    public float getUsd(){
        return mRmb / 5.6f;
    }
}
