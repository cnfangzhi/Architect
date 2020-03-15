package com.fz.architect.demo06.rxjava;

/**
 * Created by fz on 2017/12/2.
 * 被观察者
 */

public abstract class Observable<T> implements ObservableSource<T>{

    public static <T> ObservableSource<T> just(T item) {
        return onAssembly(new ObservableJust<T>(item));
    }

    private static <T> ObservableSource<T> onAssembly(ObservableJust<T> source) {
        // 留出来了
        return source;
    }

    @Override
    public void subscribe(Observer<T> observer) {
        subscribeActual(observer);
    }

    protected abstract void subscribeActual(Observer<T> observer);
}
