package com.fz.architect.demo04;

import android.os.Bundle;
import android.view.View;

import com.fz.architect.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * description:
 * author: Darren on 2017/10/31 09:36
 * email: 514905121@qq.com
 * version: 1.0
 */
public class TestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.test_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post("text");
            }
        });
    }
}
