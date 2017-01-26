package com.app.recall.util;

import android.content.Context;
import android.os.Environment;
import android.support.v4.content.ContextCompat;

import com.app.recall.R;

import java.io.File;

/**
 * Created by KenChan on 17/1/4.
 */

public class SDCardUtils {
    public static String getSDCardDirectory() {
        //如果有挂载外置sdcard,则直接返回sdcard根目录
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return Environment.getExternalStorageDirectory().getPath() + File.separator;
        }
        //否则直接用手机自带储存目录
        return Environment.getRootDirectory().getPath() + File.separator;
    }

    public static String cdApplicationSpecialDirectory(Context context, String directory) {
        cdApplicationDirectory(context);
        File file = new File(applicationDirectory(context) + directory);
        if (!file.exists()) file.mkdir();
        return file.getAbsolutePath() + File.separator;
    }

    public static boolean cdApplicationDirectory(Context context) {
        return isApplicationDirectoryExists(context) || new File(applicationDirectory(context))
                .mkdirs();
    }

    private static boolean isApplicationDirectoryExists(Context context) {
        return new File(applicationDirectory(context)).exists();
    }

    private static String applicationDirectory(Context context) {
        return getSDCardDirectory() + context.getResources().getString(R.string.app_name) + File
                .separator;
    }
}
