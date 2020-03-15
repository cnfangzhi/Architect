package com.fz.architect.demo03.retrofit;

import com.fz.architect.demo03.okhttp3.HttpUrl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * description:
 * author: fz on 2017/10/13 10:59
 * email: 514905121@qq.com
 * version: 1.0
 */
public class Retrofit {
    final HttpUrl baseUrl;

    private Retrofit(Builder builder) {
        this.baseUrl = builder.baseUrl;
    }

    public <T> T create(Class<T> service) {
        // 校验是不是一个接口

        // 是否需要缓存

        // 动态代理
        return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class<?>[]{service}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {


                //如果调用的方法是Object的
                if (method.getDeclaringClass() == Object.class) {
                    return method.invoke(this, args);
                }

                // 解析数据参数
                ServiceMethod serviceMethod = new ServiceMethod(Retrofit.this, method);

                HttpCall<?> call = new HttpCall<>(serviceMethod, args);

                return call;
            }
        });
    }


    public static class Builder {
        HttpUrl baseUrl;

        public Builder baseUrl(String baseUrl) {
            this.baseUrl = HttpUrl.parse(baseUrl);
            return this;
        }

        public Retrofit build() {
            return new Retrofit(this);
        }
    }
}
