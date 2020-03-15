package com.fz.architect.demo03.retrofit;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;

/**
 * description:
 * author: fz on 2017/10/13 12:34
 * email: 514905121@qq.com
 * version: 1.0
 */
public class Utils {
    public static Class<?> analysisClazzInfo(Object object) {
        Type genType = object.getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        return (Class) params[0];
    }

    static Type getCallResponseType(Type returnType) {
        if (!(returnType instanceof ParameterizedType)) {
            throw new IllegalArgumentException(
                    "Call return type must be parameterized as Call<Foo> or Call<? extends Foo>");
        }
        return getParameterUpperBound(0, (ParameterizedType) returnType);
    }

    static Type getParameterUpperBound(int index, ParameterizedType type) {
        Type[] types = type.getActualTypeArguments();
        if (index < 0 || index >= types.length) {
            throw new IllegalArgumentException("Index " + index
                    + " not in range [0," + types.length + ") for " + type);
        }
        Type paramType = types[index];
        if (paramType instanceof WildcardType) {
            return ((WildcardType) paramType).getUpperBounds()[0];
        }
        return paramType;
    }
}
