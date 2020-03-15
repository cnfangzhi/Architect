package com.fz.architect.design09.simple3;

import com.fz.architect.design09.retrofit2.http.FormUrlEncoded;
import com.fz.architect.design09.retrofit2.http.POST;
import com.fz.architect.design09.retrofit2.http.PartMap;

import java.util.Map;

/**
 * Created by fz on 2017/10/15.
 */

public interface ServiceInterface {
    /**
     * 用户登录
     * @return
     */
    @POST
    @FormUrlEncoded
    String userLogin(@PartMap Map<String, Object> params);

    String userRegister();
}
