package com.fz.architect.demo03;


import com.fz.architect.demo03.retrofit.Retrofit;

/**
 * description:
 * author: fz on 2017/10/12 11:14
 * email: 514905121@qq.com
 * version: 1.0
 */
public class RetrofitClient {
    private static DataServiceInterface serviceInterface;

    static {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.saiwuquan.com/")
                .build();

        serviceInterface = retrofit.create(DataServiceInterface.class);
    }

    public static DataServiceInterface getService() {
        return serviceInterface;
    }
}
