package com.fz.architect.design12.simple1;


import com.fz.architect.design12.simple1.iterator.Iterator;

/**
 * Created by fz on 2017/10/22.
 * 容器的接口
 */
public interface Aggregate<T> {
    // Aggregate 离开 Iterator 还可以用吗？不能用 ，
    // 整体 Aggregate（不能用） 局部 Iterator（可以存在）
    Iterator<T> iterator();
}
