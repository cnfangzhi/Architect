package com.fz.architect.demo06.simple2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.fz.architect.R;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        mImage = (ImageView) findViewById(R.id.image);
        // 1.观察者 Observable 被观察对象
        // Observer 观察者
        // subscribe 注册订阅
        new Thread(new Runnable() {
            @Override
            public void run() {
                Observable.just("http://img.taopic.com/uploads/allimg/130331/240460-13033106243430.jpg")
                        .map(new Function<String, Bitmap>() {
                            @Override
                            public Bitmap apply(String urlPath) throws Exception {
                                // 第五步
                                URL url = new URL(urlPath);
                                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                                InputStream inputStream = urlConnection.getInputStream();
                                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                                return bitmap;
                            }
                        })
                        .map(new Function<Bitmap, Bitmap>() {
                            @Override
                            public Bitmap apply(@NonNull Bitmap bitmap) throws Exception {
                                bitmap = createWatermark(bitmap, "RxJava2.0");
                                return bitmap;
                            }
                        })
                        .map(new Function<Bitmap, Bitmap>() {
                            @Override
                            public Bitmap apply(Bitmap bitmap) throws Exception {
                                return bitmap;
                            }
                        })

                        .subscribe(new Consumer<Bitmap>() {
                            @Override
                            public void onNext(final Bitmap bitmap) {
                                // 第七步
                                Log.e("TAG", "item = " + bitmap);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        mImage.setImageBitmap(bitmap);
                                    }
                                });
                            }
                        });
            }
        }).start();
    }

    private Bitmap createWatermark(Bitmap bitmap, String mark) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Paint p = new Paint();
        // 水印颜色
        p.setColor(Color.parseColor("#C5FF0000"));
        // 水印字体大小
        p.setTextSize(150);
        //抗锯齿
        p.setAntiAlias(true);
        //绘制图像
        canvas.drawBitmap(bitmap, 0, 0, p);
        //绘制文字
        canvas.drawText(mark, 0, h / 2, p);
        canvas.save();
        canvas.restore();
        return bmp;
    }
}
