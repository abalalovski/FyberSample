package com.twodwarfs.fyber.utils;

import android.text.TextUtils;
import android.util.Log;

import com.twodwarfs.fyber.BuildConfig;
import com.twodwarfs.fyber.cons.Constants;

/**
 * Created by Aleksandar Balalovski Balalovski
 * <abalalovski@gmail.com>
 */

public class Logger {

    public static void doLog(Object text) {
        if (BuildConfig.DEBUG && text != null && !TextUtils.isEmpty(text.toString())) {
            Log.i(Constants.TAG, text.toString());
        }
    }

    public static void doLogException(Throwable t) {
        if (BuildConfig.DEBUG && t != null) {
            Log.e(Constants.TAG, "Exception", t);
        }
    }
}
