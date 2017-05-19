package com.brayanbedritchuk.sailbeer.model.viewmodel;

import com.brayanbedritchuk.sailbeer.model.Beer;

public class BeerDetailsViewModel {

    private Beer beer;

    public Beer getBeer() {
        return beer;
    }

    public void setBeer(Beer beer) {
        this.beer = beer;
    }
}
