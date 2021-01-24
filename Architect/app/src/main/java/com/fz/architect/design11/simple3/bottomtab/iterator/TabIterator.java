package com.fz.architect.design11.simple3.bottomtab.iterator;

import com.fz.architect.design11.simple3.bottomtab.BottomTabItem;

/**
 * Created by fz on 2017/10/22.
 */

public interface TabIterator {
    BottomTabItem next();

    boolean hashNext();
}
