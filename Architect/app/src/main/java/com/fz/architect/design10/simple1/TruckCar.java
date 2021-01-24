package com.fz.architect.design10.simple1;

/**
 * Created by fz on 2017/10/21.
 * 装箱子的卡车
 */

public class TruckCar {
    public IBox box;

    public void addBox(IBox box){
        this.box = box;
    }

    public IBox remove(){
        return box;
    }
}
