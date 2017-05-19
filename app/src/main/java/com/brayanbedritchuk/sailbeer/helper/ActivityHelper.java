package com.brayanbedritchuk.sailbeer.helper;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;

public class ActivityHelper {

    public static void blockScreenOrientation(Activity activity) {
        Configuration config = activity.getResources().getConfiguration();

        if (config.orientation == Configuration.ORIENTATION_PORTRAIT) {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }

    public static void unblockScreenOrientation(Activity activity) {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
    }

}
