package com.challenge.estate.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table (name = "property")
public class Property {
    @Id
    @Column(name="listing_id")
    @GenericGenerator(name = "UseExistingIdElseGenerate", strategy = "com.challenge.estate.UseExistingIdElseGenerate")
    @GeneratedValue(generator = "UseExistingIdElseGenerate")
    private Long listingId;

    @Column(name="real_estate_type")
    private PropertyType realEstateType;

    @Column(name="street")
    private String street;

    @Column(name="house_number")
    private String houseNumber;

    @Column(name="postcode")
    private String postcode;

    @Column(name="latitude")
    private String latitude;

    @Column(name="longitude")
    private String longitude;

    @Column(name="city")
    private String city;

    @Column(name="living_area")
    private float livingArea;

    @Column(name="site_area")
    private float siteArea;

    @Column(name="rental_price")
    private long rentalPrice;

    @Column(name="sales_price")
    private long salesPrice;

    @Column(name="imageURL")
    private String imageURL;

    private @Version @JsonIgnore Long version;

    public Property() {
    }

    public Property(Long listingId, PropertyType realEstateType,
                    String street, String houseNumber, String postcode, String city,
                    float livingArea, float siteArea,
                    long rentalPrice, long salesPrice, String imageURL) {
        this.listingId = listingId;
        this.realEstateType = realEstateType;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postcode = postcode;
        this.city = city;
        this.livingArea = livingArea;
        this.siteArea = siteArea;
        this.rentalPrice = rentalPrice;
        this.salesPrice = salesPrice;
        this.imageURL = imageURL;
    }

    public Property(PropertyType realEstateType,
                    String street, String houseNumber, String postcode, String city,
                    float livingArea, float siteArea,
                    long rentalPrice, long salesPrice,
                    String imageURL) {
        this.realEstateType = realEstateType;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postcode = postcode;
        this.city = city;
        this.livingArea = livingArea;
        this.siteArea = siteArea;
        this.rentalPrice = rentalPrice;
        this.salesPrice = salesPrice;
        this.imageURL = imageURL;
    }

    public Long getListingId() {
        return listingId;
    }

    public PropertyType getRealEstateType() {
        return realEstateType;
    }

    public void setRealEstateType(PropertyType realEstateType) {
        this.realEstateType = realEstateType;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

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

    public long getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(long rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public long getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(long salesPrice) {
        this.salesPrice = salesPrice;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Property)) return false;
        Property property = (Property) o;
        return Objects.equals(getListingId(), property.getListingId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getListingId());
    }
}
