package com.javaproject.gestion_immobiliere.services;

import com.javaproject.gestion_immobiliere.entities.Owner;
import com.javaproject.gestion_immobiliere.entities.Property;
import com.javaproject.gestion_immobiliere.entities.PropertyStatus;
import com.javaproject.gestion_immobiliere.repositories.OwnerRepository;
import com.javaproject.gestion_immobiliere.repositories.PropertyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService implements IPropertyService {

    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private OwnerRepository ownerRepository;

    @Override
    public List<Property> listProperties() {
        return propertyRepository.findAll();
    }

    @Override
    public Property addProperty(Property property) {

        if (property.getOwner() != null && property.getOwner().getId() != null) {
            Optional<Owner> owner = ownerRepository.findById(property.getOwner().getId());
            owner.ifPresent(property::setOwner);
        }
        return propertyRepository.save(property);
    }

    @Override
    public Property updateProperty(Property property) {
        if (property.getId() == null) {
            throw new IllegalArgumentException("Property ID cannot be null for update operation");
        }
        if (!propertyRepository.existsById(property.getId())) {
            throw new EntityNotFoundException("Property with ID " + property.getId() + " does not exist");
        }
        return propertyRepository.save(property);
    }

    @Override
    public Property findPropertyById(Long id) {
        return propertyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Property with ID " + id + " not found"));
    }

    @Override
    public boolean deleteProperty(Long id) {
        if (propertyRepository.existsById(id)) {
            Property property = propertyRepository.findById(id).orElseThrow(() ->
                    new EntityNotFoundException("Property with ID " + id + " not found"));
            property.setStatus(PropertyStatus.ARCHIVED);
            propertyRepository.save(property);
            return true;
        }
        return false;
    }
}
