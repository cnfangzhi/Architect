package com.fz.architect.demo06.rxjava;

/**
 * Created by fz on 2017/12/2.
 */

public class ObservableJust<T> extends Observable<T> {
    private T item;
    public ObservableJust(T item) {
        this.item = item;
    }

    @Override
    protected void subscribeActual(Observer<T> observer) {
        // 代理对象，why? 方便代码扩展，
        ScalarDisposable sd = new ScalarDisposable(observer,item);
        observer.onSubscribe();
        sd.run();
    }

    private class ScalarDisposable<T>{
        private Observer observer;
        private T item;

        public ScalarDisposable(Observer<T> observer, T item) {
            this.observer = observer;
            this.item = item;
        }

        public void run(){
            try {
                observer.onNext(item);
                observer.onComplete();
            }catch (Exception e){
                observer.onError(e);
            }
        }
    }
}
