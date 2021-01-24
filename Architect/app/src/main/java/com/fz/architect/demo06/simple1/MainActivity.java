package com.fz.architect.demo06.simple1;

import android.os.Bundle;
import android.util.Log;


import com.fz.architect.R;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        // 1.观察者 Observable 被观察对象
        // Observer 观察者
        // subscribe 注册订阅
        Observable.just("urlxxx")
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.e("TAG","onSubscribe");
                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        Log.e("TAG","onNext = "+s);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("TAG","onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.e("TAG","onComplete");
                    }
                });

    }
}
