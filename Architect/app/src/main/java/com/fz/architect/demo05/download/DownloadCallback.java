package com.fz.architect.demo05.download;

import java.io.File;
import java.io.IOException;

/**
 * Created by fz on 2017/11/26.
 */

public interface DownloadCallback {
    void onFailure(IOException e);

    void onSucceed(File file);
}
