package br.udacity.utils;

import android.util.Base64;

import java.io.UnsupportedEncodingException;

/**
 * Created by jeferson on 28/11/15.
 */
public class StringUtil {
    public static String getBase64(String text) throws UnsupportedEncodingException {
        byte[] data = text.getBytes("UTF-8");
        return Base64.encodeToString(data, Base64.DEFAULT);
    }
}
