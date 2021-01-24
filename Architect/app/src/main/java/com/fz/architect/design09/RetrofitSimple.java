package com.fz.architect.design09;

import com.fz.architect.design09.retrofit2.Retrofit;
import com.fz.architect.design09.retrofit2.converter.gson.GsonConverterFactory;

/**
 * description:
 * author: fz on 2017/10/12 11:14
 * email: 514905121@qq.com
 * version: 1.0
 */
public class RetrofitSimple {
    private static DataServiceInterface serviceInterface;

    static {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.saiwuquan.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        serviceInterface = retrofit.create(DataServiceInterface.class);
    }

    public static DataServiceInterface getService() {
        return serviceInterface;
    }
}
