package com.brayanbedritchuk.sailbeer.model.viewmodel;

import com.brayanbedritchuk.sailbeer.model.Beer;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel {

    private boolean firstSession = true;
    private boolean performingAnimation;
    private final List<Beer> beerList;

    public MainViewModel() {
        this.beerList = new ArrayList<>();
    }

    public List<Beer> getBeerList() {
        return this.beerList;
    }

    public boolean isPerformingAnimation() {
        return performingAnimation;
    }

    public void setPerformingAnimation(boolean performingAnimation) {
        this.performingAnimation = performingAnimation;
    }

    public boolean isFirstSession() {
        return firstSession;
    }

    public void setFirstSession(boolean firstSession) {
        this.firstSession = firstSession;
    }
}
