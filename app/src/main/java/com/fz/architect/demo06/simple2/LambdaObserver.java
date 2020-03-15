package com.fz.architect.demo06.simple2;


import androidx.annotation.NonNull;

/**
 * Created by fz on 2017/12/3.
 */

public class LambdaObserver<T> implements Observer<T>{
    private Consumer<T> onNext;
    public LambdaObserver(Consumer<T> onNext) {
        this.onNext = onNext;
    }

    @Override
    public void onSubscribe() {

    }

    @Override
    public void onNext(@NonNull T item) {
        onNext.onNext(item);
    }

    @Override
    public void onError(@NonNull Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
