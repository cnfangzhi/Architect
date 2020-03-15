package com.fz.architect.design09;

import com.fz.architect.design09.retrofit2.Call;
import com.fz.architect.design09.retrofit2.http.GET;
import com.fz.architect.design09.retrofit2.http.Headers;
import com.fz.architect.design09.retrofit2.http.Query;

/**
 * description:
 * author: fz on 2017/10/11 10:56
 * email: 514905121@qq.com
 * version: 1.0
 */
public interface DataServiceInterface{
    @GET("api/appv2/sceneModel")
    @Headers("content:type")
    Call<Result> testMethod(@Query("age") int age);

    @GET("api/appv2/sceneModel")
    @Headers("content:type")
    Call<Result> testMethod(@Query("age") int age, @Query("name") String name);
}
