package com.fz.architect.design05;


import androidx.annotation.NonNull;

/**
 * Created by fz on 2017/10/1.
 */

public class Request implements Runnable,Comparable<Request>{
    @Override
    public void run() {
        System.out.println("run");
    }

    @Override
    public int compareTo(@NonNull Request o) {
        return 0;
    }
}
