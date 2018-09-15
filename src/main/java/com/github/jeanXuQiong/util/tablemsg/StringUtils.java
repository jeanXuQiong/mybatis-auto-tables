package com.github.jeanXuQiong.util.tablemsg;

public class StringUtils {
    public static boolean isNotBlank(String msg) {
       return null != msg && !msg.trim().equals("");
    }

    private static boolean isBlank(String msg) {
       return null == msg || msg.trim().equals("");
    }

    static boolean isNotNull(String packageName) {
        return null != packageName;
    }
}
