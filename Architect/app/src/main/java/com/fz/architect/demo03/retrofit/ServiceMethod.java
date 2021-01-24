package com.fz.architect.demo03.retrofit;

import com.fz.architect.demo03.okhttp3.HttpUrl;
import com.fz.architect.demo03.retrofit.http.annotation.FieldMap;
import com.fz.architect.demo03.retrofit.http.annotation.GET;
import com.fz.architect.demo03.retrofit.http.annotation.POST;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * description:
 * author: fz on 2017/10/13 11:19
 * email: 514905121@qq.com
 * version: 1.0
 */
public class ServiceMethod {
    String httpMethod;
    String relativeUrl;
    ParameterHandler[] parameterHandlers;
    boolean hasBody;
    HttpUrl baseUrl;
    final Method method;

    public ServiceMethod(Retrofit retrofit, Method method) {
        this.method = method;
        // 解析方法
        Annotation[] annotations = method.getAnnotations();
        for (Annotation annotation : annotations) {
            parseMethodAnnotation(annotation);
        }

        // 解析方法参数的 annotation
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();

        // 获取方法参数 - 长度
        int parameterCount = parameterAnnotations.length;
        // 创建一个 ParameterHandler 数组
        parameterHandlers = new ParameterHandler<?>[parameterCount];
        for (int i = 0; i < parameterCount; i++) {
            parseParameterAnnotation(i, parameterAnnotations[i]);
        }

        baseUrl = retrofit.baseUrl;
    }

    /**
     * 解析Parameter 属性的 Annotation
     *
     * @param index
     * @param parameterAnnotations
     */
    private void parseParameterAnnotation(int index, Annotation[] parameterAnnotations) {
        if (parameterAnnotations.length != 1) {
            throw new IllegalArgumentException("方法参数的注解只能为 1 个！");
        }

        Annotation parameterAnnotation = parameterAnnotations[0];
        if (parameterAnnotation instanceof FieldMap) {
            parameterHandlers[index] = ParameterHandler.createFieldMapHandler();
        }
        // 下面还有一些就不写了 ....

    }

    private void parseMethodAnnotation(Annotation annotation) {
        if (annotation instanceof POST) {
            parseHttpMethodAndPath("POST", ((POST) annotation).value(), true);
        }

        if (annotation instanceof GET) {
            parseHttpMethodAndPath("GET", ((GET) annotation).value(), false);
        }
    }

    private void parseHttpMethodAndPath(String httpMethod, String value, boolean hasBody) {
        this.httpMethod = httpMethod;
        this.relativeUrl = value;
        this.hasBody = hasBody;
    }

    public Type getResponseType() {
        final Type responseType = Utils.getCallResponseType(method.getGenericReturnType());
        return responseType;
    }

}
