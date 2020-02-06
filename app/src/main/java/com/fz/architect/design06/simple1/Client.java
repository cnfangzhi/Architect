package com.fz.architect.design06.simple1;

/**
 * Created by fz on 2017/10/7.
 */

public class Client {
    public static void main(String[] args){
        FinanceManager financeManager = new FinanceManager();
        float money = financeManager.finance(3,10000, FinanceManager.Finance.ZHI_FU_BAO);
        System.out.println("money = "+money);
    }
}
