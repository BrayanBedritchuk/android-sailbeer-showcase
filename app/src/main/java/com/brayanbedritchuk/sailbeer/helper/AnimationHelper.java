package com.brayanbedritchuk.sailbeer.helper;

import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.View;

public class AnimationHelper {

    public static void performScaleUpAnimation(View view, Runnable run) {
        view.animate().scaleX(1)
                .scaleY(1)
                .setInterpolator(new FastOutSlowInInterpolator())
                .setStartDelay(500)
                .withEndAction(run);
    }

}
