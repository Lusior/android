package com.longdian.util;

import android.content.Context;
import android.content.res.Configuration;

public class SystemUtils {
    public static boolean isLandscape(Context paramContext) {
        return paramContext.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }
}