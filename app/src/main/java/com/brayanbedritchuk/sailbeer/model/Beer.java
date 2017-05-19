package com.brayanbedritchuk.sailbeer.model;

import java.io.Serializable;

public class Beer implements Serializable {

    private String name;
    private String country;
    private int flagDrawableId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getFlagDrawableId() {
        return flagDrawableId;
    }

    public void setFlagDrawableId(int flagDrawableId) {
        this.flagDrawableId = flagDrawableId;
    }
}
