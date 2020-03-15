package com.fz.architect.demo03.retrofit;

/**
 * description:
 * author: fz on 2017/10/13 11:51
 * email: 514905121@qq.com
 * version: 1.0
 */
public class Response<T> {
    T body;

    /**
     * The deserialized response body of a {@linkplain #isSuccessful() successful} response.
     */
    public T body() {
        return body;
    }
}
