package com.challenge.estate;

import com.challenge.estate.models.Property;
import org.springframework.data.repository.CrudRepository;

public interface PropertyRepository extends CrudRepository <Property, Long>{
}

