package com.brayanbedritchuk.sailbeer.model;

import com.brayanbedritchuk.sailbeer.R;

public enum Country {

    ARGENTINA("Argentina", R.drawable.argentina_flag),
    BRAZIL("Brazil", R.drawable.brazil_flag),
    BELGIUM("Belgium", R.drawable.belgium_flag),
    MEXICO("Mexico", R.drawable.mexico_flag),
    NETHERLANDS("Netherlands", R.drawable.netherlands_flag),
    USA("USA", R.drawable.usa_flag);

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
