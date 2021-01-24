package com.fz.architect.demo06.rxjava;

/**
 * Created by fz on 2017/12/2.
 */

public interface ObservableSource<T> {
    void subscribe(Observer<T> observer);
}
