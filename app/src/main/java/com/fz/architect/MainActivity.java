package com.fz.architect;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.app.IntentService;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.DecelerateInterpolator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Handler mHandler = new Handler();
        ObjectAnimator.ofFloat(null, "", 0f, 1f)
                .setDuration(4000)
//                .setInterpolator(new DecelerateInterpolator())
                .start();
    }

    IntentService
}
