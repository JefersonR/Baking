package br.udacity.utils;

import br.udacity.BuildConfig;

public class Log {
    private static final boolean LOG = BuildConfig.LOG;
    private static String TAG = BuildConfig.APPLICATION_ID;

    public static void i(String tag, String string) {
        if (LOG && string != null && !string.isEmpty()) android.util.Log.i(tag, string);
    }
    public static void e(String tag, String string) {
        if (LOG && string != null && !string.isEmpty()) android.util.Log.e(tag, string);  
    }
    public static void d(String tag, String string) {
        if (LOG && string != null && !string.isEmpty()) android.util.Log.d(tag, string);
    }
    public static void v(String tag, String string) {  
        if (LOG && string != null && !string.isEmpty()) android.util.Log.v(tag, string);
    }
    public static void w(String tag, String string) { 
        if (LOG && string != null && !string.isEmpty()) android.util.Log.w(tag, string);
    }

    public static void i( String string) {
        if (LOG && string != null && !string.isEmpty()) android.util.Log.i(TAG, string);
    }
    public static void e( String string) {
        if (LOG && string != null && !string.isEmpty()) android.util.Log.e(TAG, string);
    }
    public static void d( String string) {
        if (LOG && string != null && !string.isEmpty()) android.util.Log.d(TAG, string);
    }
    public static void v( String string) {
        if (LOG && string != null && !string.isEmpty()) android.util.Log.v(TAG, string);
    }
    public static void w( String string) {
        if (LOG && string != null && !string.isEmpty()) android.util.Log.w(TAG, string);
    }
}   