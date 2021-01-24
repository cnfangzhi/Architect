package com.fz.architect.demo03;

import android.os.Bundle;
import android.util.Log;

import com.fz.architect.R;
import com.fz.architect.demo03.retrofit.Call;
import com.fz.architect.demo03.retrofit.Callback;
import com.fz.architect.demo03.retrofit.Response;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        File file = new File(getCacheDir(), "test.png");
        try {
            copyBigDataToSD(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Log.e("TAG",file.exists()+" ");

        /*String url = "https://api.saiwuquan.com/api/upload";
        //String url = "https://www.baidu.com";

        RequestBody requestBody = new RequestBody()
                .setType(RequestBody.FORM)
                .addFormDataPart("file1", RequestBody.create(file))
                .addFormDataPart("file2", RequestBody.create(file))
                .addFormDataPart("platform", "android");

        final Request request = new Request.Builder()
                .url(url)
                .tag(this)
                .post(requestBody)
                .build();

        OkHttpClient okHttpClient = new OkHttpClient();

        okHttpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                //e.printStackTrace();
                Log.e("TAG", "出错了");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.string();
                Log.e("TAG", result);
            }
        });*/

        Map<String, Object> params = new HashMap<>();
        params.put("pageNo", 1);
        params.put("pageSize", 1);
        params.put("platform","android");
        Call<Result> call = RetrofitClient.getService().testMethod(params);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                //Result result = response.body();
                Log.e("TAG", "result = " + response.body().msg);
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                t.printStackTrace();
                Log.e("TAG", "出错了");
            }
        });
    }

    private void copyBigDataToSD(File file) throws IOException {
        try {
            InputStream myInput;
            OutputStream myOutput = new FileOutputStream(file);
            myInput = this.getAssets().open("test.png");
            byte[] buffer = new byte[1024];
            int length = myInput.read(buffer);
            while (length > 0) {
                myOutput.write(buffer, 0, length);
                length = myInput.read(buffer);
            }
            myOutput.flush();
            myInput.close();
            myOutput.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
