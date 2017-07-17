package com.example.hp4740.testapp.utils;


import android.support.annotation.Nullable;

public final class StringUtils {

    private StringUtils() {}

    public static boolean isEmptyOrBlank(@Nullable final String str){
        return str == null
                || str.length() == 0
                || str.trim().length() == 0;
    }

    public static boolean isNotEmptyNorBlank(@Nullable final String str){
        return str == null
                || str.length() == 0
                || str.trim().length() == 0;
    }

}
