package com.fz.architect.demo03.retrofit;


import android.util.Log;

import com.fz.architect.demo03.okhttp3.OkHttpClient;
import com.fz.architect.demo03.okhttp3.Request;
import com.fz.architect.demo03.okhttp3.RequestBody;
import com.google.gson.Gson;

import java.io.IOException;

/**
 * description:
 * author: fz on 2017/10/13 11:54
 * email: 514905121@qq.com
 * version: 1.0
 */
public class HttpCall<T> implements Call<T> {
    final ServiceMethod serviceMethod;
    final Object[] args;

    public HttpCall(ServiceMethod serviceMethod, Object[] args) {
        this.serviceMethod = serviceMethod;
        this.args = args;
    }

    @Override
    public Response<T> execute() throws IOException {
        return null;
    }

    @Override
    public void enqueue(final Callback<T> callback) {
        String url = serviceMethod.baseUrl.getUrl() + serviceMethod.relativeUrl;
        final Request.Builder requestBuilder = new Request.Builder()
                .url(url);

        if (serviceMethod.hasBody) {
            RequestBody requestBody = new RequestBody()
                    .setType(RequestBody.FORM);
            requestBuilder.post(requestBody);
        }

        Request request = requestBuilder.build();

        ParameterHandler[] parameterHandlers = serviceMethod.parameterHandlers;
        int parameterHandlerLength = parameterHandlers.length;

        try {
            for (int i = 0; i < parameterHandlerLength; i++) {
                parameterHandlers[i].apply(request, args[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        OkHttpClient okHttpClient = new OkHttpClient();

        okHttpClient.newCall(request).enqueue(new com.fz.architect.demo03.okhttp3.Callback() {

            @Override
            public void onFailure(com.fz.architect.demo03.okhttp3.Call call, IOException e) {
                callback.onFailure(HttpCall.this, e);
            }

            @Override
            public void onResponse(com.fz.architect.demo03.okhttp3.Call call, com.fz.architect.demo03.okhttp3.Response oldResponse) throws IOException {
                String resultStr = oldResponse.string();

                Log.e("TAG", "str " + resultStr);

                try {
                    T result = new Gson().fromJson(resultStr, serviceMethod.getResponseType());
                    Response<T> response = new Response<>();
                    response.body = result;
                    callback.onResponse(HttpCall.this, response);
                } catch (Exception e) {
                    onFailure(call, new IOException(e));
                }
            }
        });
    }

    @Override
    public boolean isExecuted() {
        return false;
    }

    @Override
    public void cancel() {

    }

    @Override
    public boolean isCanceled() {
        return false;
    }

    @Override
    public Call<T> clone() {
        return null;
    }
}
