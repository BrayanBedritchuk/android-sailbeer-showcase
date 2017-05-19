package com.brayanbedritchuk.sailbeer.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesHelper {

    private static final String PREFERENCE_FILE_KEY = "com.brayanbedritchuk.sailbeer.PREFERENCE_FILE_KEY";
    private static final String INITIAL_ANIMATION = "INITIAL_ANIMATION";

    public static boolean wasInitialAnimationShown(Context context) {
        SharedPreferences sharedPref = getSharedPreferences(context);
        return sharedPref.getBoolean(INITIAL_ANIMATION, false);
    }

    public static void setInitialAnimationAsShown(Context context) {
        getEditor(context).putBoolean(INITIAL_ANIMATION, true).commit();
    }

    public static void removeInitialAnimationAsShown(Context context) {
        getEditor(context).remove(INITIAL_ANIMATION).commit();
    }

    public static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
    }

    private static SharedPreferences.Editor getEditor(Context context) {
        return getSharedPreferences(context).edit();
    }

}
