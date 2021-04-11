package com.challenge.estate.models;

public class PropertyDimensions {
    float livingArea;
    float siteArea;

    public float getLivingArea() {
        return livingArea;
    }

    public void setLivingArea(float livingArea) {
        this.livingArea = livingArea;
    }

    public float getSiteArea() {
        return siteArea;
    }

    public void setSiteArea(float siteArea) {
        this.siteArea = siteArea;
    }

    @Override
    public String toString() {
        return "PropertyDimensions{" +
                "livingArea=" + livingArea +
                ", siteArea=" + siteArea +
                '}';
    }
}
