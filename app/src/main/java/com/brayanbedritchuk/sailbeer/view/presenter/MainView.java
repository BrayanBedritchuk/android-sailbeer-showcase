package com.brayanbedritchuk.sailbeer.view.presenter;

import android.content.Context;

public interface MainView {

    Context getActivityContext();

    void performInitialAnimation();

    void performCardBeersAnimation();

    void updateBeerList();

    void showToast(String message);
}
