package com.fz.architect.demo03.okhttp3;

import java.io.IOException;
import java.io.OutputStream;

/**
 * description:
 * author: fz on 2017/10/9 18:13
 * email: 514905121@qq.com
 * version: 1.0
 */
public interface Binary {
    String mimeType();

    String fileName();

    void writeBinary(OutputStream outputStream) throws IOException;

    long getContentLength();
}
