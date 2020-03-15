package com.fz.architect.demo06.simple3;

/**
 * Created by fz on 2017/12/3.
 */

public interface Consumer<T> {
    void onNext(T item) throws Exception;
}
