package com.fz.architect.demo03;


import com.fz.architect.demo03.retrofit.Call;
import com.fz.architect.demo03.retrofit.http.annotation.FieldMap;
import com.fz.architect.demo03.retrofit.http.annotation.POST;

import java.util.Map;

/**
 * description:
 * author: fz on 2017/10/11 10:56
 * email: 514905121@qq.com
 * version: 1.0
 */
public interface DataServiceInterface {
    @POST("api/appv2/sceneModel")
    Call<Result> testMethod(@FieldMap Map<String, Object> bodyMap);
}
