package com.fz.architect.design03.simple2;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.fz.architect.R;

public class MainActivity extends AppCompatActivity {
    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mTextView = (TextView) findViewById(R.id.tv1);
        // 问题的分析 ？ 我们一般都会有一个清理缓存的功能的 ，是不是以后需要去清理，
        // 清理的时候某些特定内容我们可能不想清理，可能以后为了保证性能，我们采取磁盘的存储内存的存储或者采取数据库的存储等等
        PreferencesUtils.getInstance()
                .saveString("userName","darren")
                .saveString("userAge","880223")
                .commit();
    }

    public void click(View view){
        String userName = PreferencesUtils.getInstance().getString("userName");
        String userAge = PreferencesUtils.getInstance().getString("userAge");
        mTextView.setText("userName = "+userName+" userAge = "+userAge);
    }
}
