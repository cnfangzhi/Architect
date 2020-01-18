package com.fz.architect.day04;

import android.util.Log;

/**
 * Created by hcDarren on 2017/9/4.
 */

public class TestBean {
    private String name="darren";// 属性 String类型 name名称 = darren值   Field

    public TestBean(String name){// 构造函数
        this.name = name;
    }

    private void sysName(){
        Log.e("TAG",name);
    }
}
