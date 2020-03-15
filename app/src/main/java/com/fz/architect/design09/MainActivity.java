package com.fz.architect.design09;

import android.os.Bundle;

import com.fz.architect.R;
import com.fz.architect.design09.simple3.ServiceInterface;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DarrenRetrofit retrofit = new DarrenRetrofit();
        // 核心代码 ServiceInterface.class 接口的 Class 会返回一个 ServiceInterface 的实例对象
        ServiceInterface serviceInterface = retrofit.create(ServiceInterface.class);
        // 能看懂
        // String result = serviceInterface.userLogin();
        // Log.e("TAG","返回值 = "+result);

        serviceInterface.userRegister();
    }
}
