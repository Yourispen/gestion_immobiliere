package com.javaproject.gestion_immobiliere.services;

import com.javaproject.gestion_immobiliere.entities.Property;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPropertyService {
    List<Property> listProperties();
    Property addProperty(Property property);
    Property updateProperty(Property property);
    Property findPropertyById(Long id);
    boolean deleteProperty(Long id);
}
