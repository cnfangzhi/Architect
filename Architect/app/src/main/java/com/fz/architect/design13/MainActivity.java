package com.fz.architect.design13;

import android.os.Bundle;
import android.os.Message;

import com.fz.architect.R;

import org.greenrobot.eventbus.EventBus;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Message message = new Message();
        Message message = Message.obtain();
        EventBus.getDefault().register(this);
        EventBus.getDefault().post(new Message());


    }

    // 下周六走源码，自己动手写核心部分
}
