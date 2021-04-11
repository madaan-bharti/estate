package com.challenge.estate.models;

public enum PropertyType {
    APARTMENT_BUY,
    APARTMENT_RENT,
    HOUSE_BUY,
    HOUSE_RENT,
    ;

    @Override
    public String toString() {
        return "PropertyType{}";
    }
}
