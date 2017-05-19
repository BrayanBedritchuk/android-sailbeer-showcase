package com.brayanbedritchuk.sailbeer.persistence;

import com.brayanbedritchuk.sailbeer.model.Beer;

import java.util.List;

public class BeersJSONList {

    private List<Beer> beers;

    public List<Beer> getBeers() {
        return beers;
    }

    public void setBeers(List<Beer> beers) {
        this.beers = beers;
    }
}
