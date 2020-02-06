package com.fz.architect.design04.simple4;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.fz.architect.R;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            FileReader fr = new FileReader("xxxx.file");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
