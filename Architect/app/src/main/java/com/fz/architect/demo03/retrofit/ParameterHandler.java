package com.fz.architect.demo03.retrofit;

import com.fz.architect.demo03.okhttp3.Request;
import com.fz.architect.demo03.okhttp3.RequestBody;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * description:
 * author: fz on 2017/10/13 11:31
 * email: 514905121@qq.com
 * version: 1.0
 */
abstract class ParameterHandler<T> {
    abstract void apply(Request request, T value) throws IOException;

    public static ParameterHandler createFieldMapHandler() {
        return new ParameterHandler<Map<String, Object>>() {
            @Override
            void apply(Request request, Map<String, Object> value) throws IOException {
                for (Map.Entry<String, Object> entry : value.entrySet()) {
                    if (entry.getValue() instanceof File) {
                        File file = (File) entry.getValue();
                        request.getRequestBody().addFormDataPart(entry.getKey(), RequestBody.create(file));
                    } else {
                        request.getRequestBody().addFormDataPart(entry.getKey(), entry.getValue() + "");
                    }
                }
            }
        };
    }
}
