package com.fz.architect.demo03.retrofit.http.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * description: retrofit POST 请求
 * author: fz on 2017/10/13 11:01
 * email: 514905121@qq.com
 * version: 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GET {
    String value() default "";
}
