package com.fz.architect.demo03.retrofit;

/**
 * description:
 * author: fz on 2017/10/13 11:50
 * email: 514905121@qq.com
 * version: 1.0
 */
public interface Callback<T> {
    /**
     * Invoked for a received HTTP response.
     * <p>
     * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
     * Call {@link Response#isSuccessful()} to determine if the response indicates success.
     */
    void onResponse(Call<T> call, Response<T> response);

    /**
     * Invoked when a network exception occurred talking to the server or when an unexpected
     * exception occurred creating the request or processing the response.
     */
    void onFailure(Call<T> call, Throwable t);
}
