package com.fz.architect.design09.simple1;

/**
 * Created by fz on 2017/10/15.
 */

public class Client {
    public static void main(String[] args){
        Man man = new Man("Darren");
        BankWorker bankWorker = new BankWorker(man);
        bankWorker.applyBank();
    }
}
