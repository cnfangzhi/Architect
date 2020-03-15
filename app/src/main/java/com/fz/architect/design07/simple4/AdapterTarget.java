package com.fz.architect.design07.simple4;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by fz on 2017/10/8.
 * 这个类相当于 UsdTarget 目标接口
 */

public interface AdapterTarget {
    /**
     * 获取多少条
     * @return
     */
    int getCount();

    /**
     * 获取View
     * @param position
     * @param parent
     * @return
     */
    View getView(int position, ViewGroup parent);
}
