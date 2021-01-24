package com.fz.architect.demo03.okhttp3;

import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import javax.net.ssl.HttpsURLConnection;

/**
 * description:
 * author: fz on 2017/10/9 15:37
 * email: 514905121@qq.com
 * version: 1.0
 */
public class RealCall implements Call {

    private final OkHttpClient client;

    // Guarded by this.
    private boolean executed;

    /**
     * The application's original request unadulterated by redirects or auth headers.
     */
    Request originalRequest;

    public RealCall(OkHttpClient client, Request originalRequest) {
        this.client = client;
        this.originalRequest = originalRequest;
    }

    @Override
    public Request request() {
        return originalRequest;
    }

    @Override
    public Response execute() throws IOException {
        synchronized (this) {
            if (executed) throw new IllegalStateException("Already Executed");
            executed = true;
        }
        try {
            client.dispatcher().executed(this);
            Response result = getResponseWithInterceptorChain();
            if (result == null) throw new IOException("Canceled");
            return result;
        } finally {
            client.dispatcher().finished(this);
            return null;
        }
    }

    @Override
    public void enqueue(Callback responseCallback) {
        synchronized (this) {
            if (executed) throw new IllegalStateException("Already Executed");
            executed = true;
        }
        client.dispatcher().enqueue(new AsyncCall(responseCallback));
    }

    @Override
    public void cancel() {
        // 也先留着
    }

    @Override
    public boolean isExecuted() {
        return executed;
    }

    @Override
    public boolean isCanceled() {
        // 是否被取消了，也先留着
        return false;
    }

    private Response getResponseWithInterceptorChain() throws IOException {
        // 主要是一些拦截器
        return null;
    }

    HttpUrl redactedUrl() {
        return originalRequest.url().resolve("/...");
    }

    final class AsyncCall extends NamedRunnable {
        private final Callback responseCallback;

        private AsyncCall(Callback responseCallback) {
            super("OkHttp %s", redactedUrl().toString());
            this.responseCallback = responseCallback;
        }

        Request request() {
            return originalRequest;
        }

        RealCall get() {
            return RealCall.this;
        }

        @Override
        protected void execute() {
            try {
                // 请求数据封装成 Response
                Log.e("TAG", "请求数据");
                Request request = request();

                HttpURLConnection httpURLConnection = (HttpURLConnection) request.url().url().openConnection();

                if (httpURLConnection instanceof HttpsURLConnection) {
                    HttpsURLConnection httpsURLConnection = (HttpsURLConnection) httpURLConnection;
                    // 设置一些证书
                    //httpsURLConnection.setSSLSocketFactory();
                    //httpsURLConnection.setHostnameVerifier();
                }

                httpURLConnection.setRequestMethod(request.getMethod().value());
                httpURLConnection.setDoOutput(request.getMethod().isOut());
                httpURLConnection.setDoInput(true);

                // 把头部提交一下
                Headers headers = request.getHeaders();
                int headerSize = headers.size();
                for (int i = 0; i < headerSize; i++) {
                    httpURLConnection.setRequestProperty(headers.name(i), headers.value(i));
                }
                RequestBody requestBody = request.getRequestBody();
                if (requestBody != null) {
                    String contentType = requestBody.getContentType();
                    httpURLConnection.setRequestProperty("Content-Type", contentType);

                    long contentLength = requestBody.getContentLength();
                    httpURLConnection.setRequestProperty("Content-Length", contentLength + "");

                    Log.e("TAG111",contentType+"  "+contentLength);
                }

                Log.e("TAG", "responseCode = 请求链接");

                // post 提交数据和文件数据
                httpURLConnection.connect();

                // httpURLConnection.setRequestProperty("Content-Type","");
                // httpURLConnection.setRequestProperty("Content-Length","");

                if (request.getMethod().isOut()) {
                    requestBody.onWriteBody(httpURLConnection.getOutputStream());
                }

                int responseCode = httpURLConnection.getResponseCode();
                Log.e("TAG", "responseCode = " + responseCode);
                Map<String, List<String>> responseHeaders = httpURLConnection.getHeaderFields();

                if (hasBody(responseCode)) {
                    /*InputStream inputStream = getRealInputStream(responseCode, responseHeaders, httpURLConnection);
                    httpURLConnection.getInputStream();

                    // 把 InputStream 转成 String
                    String result = Util.convertStream2String(inputStream);*/
                    InputStream inputStream = getRealInputStream(responseCode, responseHeaders, httpURLConnection);
                    Response response = new Response(inputStream, responseHeaders);

                    responseCallback.onResponse(RealCall.this, response);
                }

            } catch (Exception e) {
                e.printStackTrace();
                responseCallback.onFailure(RealCall.this, new IOException(e));
            } finally {
                client.dispatcher().finished(this);
            }
        }
    }

    private InputStream getRealInputStream(int responseCode, Map<String, List<String>> responseHeaders,
                                           HttpURLConnection httpURLConnection) throws IOException {
        InputStream inputStream;
        if (responseCode >= 400) {
            inputStream = httpURLConnection.getErrorStream();
        } else {
            inputStream = httpURLConnection.getInputStream();
        }
        String contentEncoding = httpURLConnection.getContentEncoding();
        if (!TextUtils.isEmpty(contentEncoding) && contentEncoding.contains("gzip")) {
            inputStream = new GZIPInputStream(inputStream);
        }
        return inputStream;
    }

    private boolean hasBody(int responseCode) {
        return !(responseCode >= 100 && responseCode < 200)
                || responseCode != 204 || responseCode != 205
                || !(responseCode >= 300 && responseCode < 400);
    }
}
