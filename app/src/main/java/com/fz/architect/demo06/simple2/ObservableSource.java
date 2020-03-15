package com.fz.architect.demo06.simple2;

/**
 * Created by fz on 2017/12/2.
 */

public interface ObservableSource<T> {
    void subscribe(Observer<T> observer);
}
