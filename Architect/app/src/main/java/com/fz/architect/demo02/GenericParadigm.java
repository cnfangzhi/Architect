package com.fz.architect.demo02;

import android.view.View;

/**
 * Created by fz on 2017/9/4.
 */

public abstract class GenericParadigm<T> {

    public abstract void onSuccess(T result);

    public abstract <D> void add(D d);

    public abstract void callback(Class<? extends GenericParadigm> clazz);

    public abstract void callback1(Class<? super GenericParadigm> clazz);

    public abstract <A extends View,B> A findView(B b);
}
