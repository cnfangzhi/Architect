package com.fz.architect.demo03.retrofit.http.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * description:
 * author: fz on 2017/10/13 11:05
 * email: 514905121@qq.com
 * version: 1.0
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldMap {
    /** Specifies whether the names and values are already URL encoded. */
    boolean encoded() default false;
}
