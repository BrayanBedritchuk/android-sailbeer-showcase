package com.brayanbedritchuk.sailbeer.model;

import com.brayanbedritchuk.sailbeer.R;

public enum Country {

    ARGENTINA("Argentina", R.drawable.ic_argentina),
    BRAZIL("Brazil", R.drawable.ic_brazil),
    BELGIUM("Belgium", R.drawable.ic_belgium),
    MEXICO("Mexico", R.drawable.ic_mexico),
    NETHERLANDS("Netherlands", R.drawable.ic_netherlands),
    USA("USA", R.drawable.ic_usa);

    Country(String name, int flagId) {
        this.name = name;
        this.flagId = flagId;
    }

    private final String name;
    private final int flagId;

    public String getName() {
        return name;
    }

    public int getFlagId() {
        return flagId;
    }
}
