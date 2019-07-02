package com.longdian.util;

import java.util.regex.Pattern;

/**
 * Created by phoenix on 2017/4/23.
 */

public class CheckUtil {

    public static boolean isPhoneNumber(String var1) {
        return Pattern.compile("^((1[0-9][0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$").matcher(var1).matches();
    }

    public static boolean isVcode(String code) {
        return code != null && code.length() == 4 && Pattern.compile("[0-9]{4}").matcher(code).matches();
    }
}
