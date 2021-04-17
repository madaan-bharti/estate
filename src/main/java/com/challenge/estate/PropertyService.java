package com.challenge.estate;

import com.challenge.estate.models.Property;
import com.challenge.estate.models.PropertyType;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyService {
    @Autowired
    PropertyRepository propertyRepository;

    public long getPropertiesCount() {
        return propertyRepository.count();
    }

    public List<Property> getAllProperties(String realEstateType,
                                           Long minPrice, Long maxPrice) {
        List<Property> properties = (List<Property>) propertyRepository.findAll();
        properties = this.filterPropertiesByRealEstateType(properties, realEstateType);
        properties = this.filterPropertiesByPrice(properties, realEstateType, minPrice, maxPrice);
        return properties;
    }

    public Property getPropertyById(long id) {
        try {
            return propertyRepository.findById(id).get();
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            return null;
        }
    }

    public List<Property> filterPropertiesByRealEstateType(List<Property> properties,
                                                           String realEstateType) {
        if (realEstateType == null || realEstateType.isEmpty()) {
            return properties;
        }

        if (!EnumUtils.isValidEnum(PropertyType.class, realEstateType)){
            return new ArrayList<>();
        }

        return properties.stream()
                .filter(property -> property.getRealEstateType().equals(PropertyType.valueOf(realEstateType)))
                .collect(Collectors.toList());
    }

    public List<Property> filterPropertiesByPrice(List<Property> properties,
                                                  String realEstateType,
                                                  Long minPrice, Long maxPrice) {
        if (realEstateType == null || realEstateType.isEmpty()) {
            return properties;
        }

        if (!EnumUtils.isValidEnum(PropertyType.class, realEstateType)){
            return new ArrayList<>();
        }

        long finalMaxPrice = (maxPrice == null) ? Long.MAX_VALUE : maxPrice;
        long finalMinPrice = (minPrice == null) ? Long.MIN_VALUE : minPrice;

        return properties.stream()
                .filter(property -> {
                    PropertyType type = PropertyType.valueOf(realEstateType);
                    long actualPrice = type.equals(PropertyType.APARTMENT_BUY) || type.equals(PropertyType.HOUSE_BUY) ?
                            property.getSalesPrice() : property.getRentalPrice();
                    return actualPrice <= finalMaxPrice && actualPrice >= finalMinPrice;
                }).collect(Collectors.toList());
    }

    public List<Property> filterPropertiesByMinPrice(List<Property> properties,
                                                     String realEstateType,
                                                     Long price) {
        return this.filterPropertiesByPrice(properties, realEstateType, price, null);
    }

    public List<Property> filterPropertiesByMaxPrice(List<Property> properties,
                                                     String realEstateType,
                                                     Long price) {
        return this.filterPropertiesByPrice(properties, realEstateType, null, price);
    }

    public void save(Property property) {
        propertyRepository.save(property);
    }

    public void saveAll(List<Property> properties) {
        propertyRepository.saveAll(properties);
    }

    public void delete(long id) {
        propertyRepository.deleteById(id);
    }
}
