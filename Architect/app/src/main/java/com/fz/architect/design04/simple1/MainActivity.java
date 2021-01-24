package com.fz.architect.design04.simple1;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.fz.architect.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 一般的写法new对象调用方法
        TeacherEat teacherEat = new TeacherEat();
        teacherEat.eat();
        // 装饰设计模式怎么写，一般情况都是把类对象作为构造参数传递

    }
}
