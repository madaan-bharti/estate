package com.challenge.estate;

import com.challenge.estate.models.Property;
import com.challenge.estate.models.PropertyType;
import org.h2.tools.Csv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@RestController
@Component
public class PropertyController implements CommandLineRunner{

    @Autowired
    PropertyService propertyService;

    @GetMapping("/properties/count")
    private long getPropertiesCount() {
        return propertyService.getPropertiesCount();
    }

    @GetMapping("/properties")
    private List<Property> getAllProperties(@Param("realEstateType") String realEstateType,
                                            @Param("minPrice") Long minPrice,
                                            @Param("maxPrice") Long maxPrice) {
        return propertyService.getAllProperties(realEstateType, minPrice, maxPrice);
    }

    @GetMapping("/properties/{id}")
    private Property getPropertyById(@PathVariable("id") long id) {
        return propertyService.getPropertyById(id);
    }

    @DeleteMapping("/properties/{id}")
    private void deleteProperty(@PathVariable("id") long id) {
        propertyService.delete(id);
    }

    @PostMapping("/properties")
    private long saveStudent(@RequestBody Property property) {
        propertyService.save(property);
        return property.getListingId();
    }

    @Override
    public void run(String... args) throws Exception {
        String fileName = "static/properties.csv";
        ClassLoader classLoader = getClass().getClassLoader();
        String filePath = new File(classLoader.getResource(fileName).getFile()).getAbsolutePath();
        ResultSet rs = new Csv().read(filePath, null, null);

        List<Property> properties = new ArrayList<>();
        while (rs.next()) {
            Property property = new Property(rs.getLong(1), PropertyType.valueOf(rs.getString(2)),
                    rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),
                    rs.getFloat(7), rs.getFloat(8),
                    rs.getLong(9), rs.getLong(10), rs.getString(11));
            properties.add(property);
        }
        propertyService.saveAll(properties);
    }
}
