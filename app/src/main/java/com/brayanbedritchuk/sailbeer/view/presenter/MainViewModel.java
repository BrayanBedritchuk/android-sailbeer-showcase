package com.brayanbedritchuk.sailbeer.view.presenter;

import com.brayanbedritchuk.sailbeer.model.Beer;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel {

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

}
