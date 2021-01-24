package com.fz.architect.design01;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.fz.architect.R;
import com.fz.architect.design01.manager.ActivityManager;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().attach(this);
        setContentView(R.layout.activity_main);
        setTitle("LoginActivity");
    }

    public void click(View view){
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        ActivityManager.getInstance().detach(this);
        super.onDestroy();
    }
}
