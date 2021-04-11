package com.challenge.estate.models;

public class PropertyPrice {
    long rentalPrice;
    long salesPrice;


    public PropertyPrice() {
    }

    public PropertyPrice(long rentalPrice, long salesPrice) {
        this.rentalPrice = rentalPrice;
        this.salesPrice = salesPrice;
    }

    public long getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(long salesPrice) {
        this.salesPrice = salesPrice;
    }

    public long getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(long rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    @Override
    public String toString() {
        return "PropertyPrice{" +
                "rentalPrice=" + rentalPrice +
                ", salesPrice=" + salesPrice +
                '}';
    }
}
