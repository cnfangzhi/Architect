package com.fz.architect.demo06.simple2;

/**
 * Created by fz on 2017/12/3.
 */

public interface Consumer<T> {
    void onNext(T item);
}
