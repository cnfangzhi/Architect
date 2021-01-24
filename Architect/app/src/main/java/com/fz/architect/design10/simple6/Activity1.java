package com.fz.architect.design10.simple6;

import android.content.Intent;
import android.os.Bundle;

import com.fz.architect.R;

import androidx.appcompat.app.AppCompatActivity;

public class Activity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 三个参数传递到 Activity 2

        Intent intent = new Intent(this,Activity2.class);

        intent.putExtra("Params1","Params1");
        intent.putExtra("Params2","Params2");
        intent.putExtra("Params3","Params3");

        startActivity(intent);
    }
}
