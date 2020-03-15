package com.fz.architect.design07;

import android.os.Bundle;

import com.fz.architect.R;
import com.fz.architect.design07.simple4.DarrenListView;
import com.fz.architect.design07.simple4.ListAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private DarrenListView mListView;
    private List<String> items = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        mListView = (DarrenListView) findViewById(R.id.darren_lv);
        // 假设后台返回的数据是一个集合，我们要显示的是列表，是 View，
        
        for (int i=0;i<100;i++){
            items.add(i+"");
        }

        // ListView 需要的是 View ，后台给我们返回的是 列表对象数组，我们采用适配器模式去适配

        // 一般写法,for循环不断的添加 View
        /*for (String item : items) {
            TextView itemView = (TextView) LayoutInflater.from(this).inflate(R.layout.item_main,null);
            itemView.setText(item);
            mListView.addView(itemView);
        }*/

        // adapter 设计模式
        mListView.setAdapter(new ListAdapter(items,this));
    }
}
