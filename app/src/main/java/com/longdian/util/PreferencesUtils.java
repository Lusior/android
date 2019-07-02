package com.longdian.util;

import android.content.Context;
import android.content.SharedPreferences.Editor;

public class PreferencesUtils {
    public static String PREFERENCE_NAME = "kding";

    private PreferencesUtils() {
        throw new AssertionError();
    }

    public static boolean getBoolean(Context paramContext, String paramString) {
        return getBoolean(paramContext, paramString, false);
    }

    public static boolean getBoolean(Context paramContext, String paramString, boolean paramBoolean) {
        return paramContext.getSharedPreferences(PREFERENCE_NAME, 0).getBoolean(paramString, paramBoolean);
    }

    public static float getFloat(Context paramContext, String paramString) {
        return getFloat(paramContext, paramString, -1.0F);
    }

    public static float getFloat(Context paramContext, String paramString, float paramFloat) {
        return paramContext.getSharedPreferences(PREFERENCE_NAME, 0).getFloat(paramString, paramFloat);
    }

    public static int getInt(Context paramContext, String paramString) {
        return getInt(paramContext, paramString, -1);
    }

    public static int getInt(Context paramContext, String paramString, int paramInt) {
        return paramContext.getSharedPreferences(PREFERENCE_NAME, 0).getInt(paramString, paramInt);
    }

    public static long getLong(Context paramContext, String paramString) {
        return getLong(paramContext, paramString, -1L);
    }

    public static long getLong(Context paramContext, String paramString, long paramLong) {
        return paramContext.getSharedPreferences(PREFERENCE_NAME, 0).getLong(paramString, paramLong);
    }

    public static String getString(Context paramContext, String paramString) {
        return getString(paramContext, paramString, null);
    }

    public static String getString(Context paramContext, String paramString1, String paramString2) {
        return paramContext.getSharedPreferences(PREFERENCE_NAME, 0).getString(paramString1, paramString2);
    }

    public static boolean putBoolean(Context paramContext, String paramString, boolean paramBoolean) {
        Editor localEditor = paramContext.getSharedPreferences(PREFERENCE_NAME, 0).edit();
        localEditor.putBoolean(paramString, paramBoolean);
        return localEditor.commit();
    }

    public static boolean putFloat(Context paramContext, String paramString, float paramFloat) {
        Editor localEditor = paramContext.getSharedPreferences(PREFERENCE_NAME, 0).edit();
        localEditor.putFloat(paramString, paramFloat);
        return localEditor.commit();
    }

    public static boolean putInt(Context paramContext, String paramString, int paramInt) {
        Editor localEditor = paramContext.getSharedPreferences(PREFERENCE_NAME, 0).edit();
        localEditor.putInt(paramString, paramInt);
        return localEditor.commit();
    }

    public static boolean putLong(Context paramContext, String paramString, long paramLong) {
        Editor localEditor = paramContext.getSharedPreferences(PREFERENCE_NAME, 0).edit();
        localEditor.putLong(paramString, paramLong);
        return localEditor.commit();
    }

    public static boolean putString(Context paramContext, String paramString1, String paramString2) {
        Editor localEditor = paramContext.getSharedPreferences(PREFERENCE_NAME, 0).edit();
        localEditor.putString(paramString1, paramString2);
        return localEditor.commit();
    }
}