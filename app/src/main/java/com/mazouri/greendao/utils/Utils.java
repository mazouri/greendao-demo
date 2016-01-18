package com.mazouri.greendao.utils;

import android.util.Log;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by wangdong on 16-1-18.
 */
public class Utils {

    private static final int BUFFER_SIZE = 32768;

    public static void copyStream(InputStream is, OutputStream os) {
        try {
            final byte[] bytes = new byte[BUFFER_SIZE];

            int count = is.read(bytes, 0, BUFFER_SIZE);
            while (count > -1) {
                os.write(bytes, 0, count);
                count = is.read(bytes, 0, BUFFER_SIZE);
            }

            os.flush();
        } catch (Exception ex) {
            Log.e("", "", ex);
        }
    }
}
