package com.fz.architect.demo06.simple2;


import androidx.annotation.NonNull;

/**
 * Created by fz on 2017/12/2.
 */

public class ObservableMap<T,R> extends Observable<R> {
    final Observable<T> source;// 前面的 Observable
    final Function<T, R> function;// 当前转换
    public ObservableMap(Observable<T> source, Function<T, R> function) {
        this.source = source;
        this.function = function;
    }

    @Override
    protected void subscribeActual(Observer<R> observer) {
        // 第一步
        // 对 observer 包裹了一层
        source.subscribe(new MapObserver(observer,function));
    }

    private class MapObserver<T> implements Observer<T>{
        final Observer<R> observer;
        final Function<T, R> function;
        public MapObserver(Observer<R> source, Function<T, R> function) {
            this.observer = source;
            this.function = function;
        }

        @Override
        public void onSubscribe() {
            observer.onSubscribe();
        }

        @Override
        public void onNext(@NonNull T item) {
            // item 是 String  xxxUrl
            // 要去转换 String -> Bitmap
            // 4.第四步 function.apply
            try {
                R applyR = function.apply(item);
                // 6. 第六步，调用 onNext
                // 把 Bitmap 传出去
                observer.onNext(applyR);
            } catch (Exception e) {
                e.printStackTrace();
                observer.onError(e);
            }
        }

        @Override
        public void onError(@NonNull Throwable e) {
            observer.onError(e);
        }

        @Override
        public void onComplete() {
            observer.onComplete();
        }
    }
}
