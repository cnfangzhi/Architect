package com.fz.architect.design11.simple2.iterator;

/**
 * Created by fz on 2017/10/22.
 * 迭代器的接口
 */
public interface Iterator<T> {
    /**
     * 获取下一个
     * @return
     */
    T next();

    /**
     * 是否有下一个
     * @return
     */
    boolean hasNext();
}
