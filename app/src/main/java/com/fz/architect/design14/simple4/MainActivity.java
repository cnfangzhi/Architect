package com.fz.architect.design14.simple4;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by fz on 2017/11/4.
 */

public class MainActivity extends AppCompatActivity {
    public TextView tv1;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ButterKnife.bind(this);
        MainActivity$$Binder binder = new MainActivity$$Binder();
        binder.bind(this);
    }
}
