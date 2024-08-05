package com.javaproject.gestion_immobiliere.controllers;

import com.javaproject.gestion_immobiliere.entities.Property;
import com.javaproject.gestion_immobiliere.services.IPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    @Autowired
    private IPropertyService propertyService;

    @GetMapping
    public ResponseEntity<List<Property>> listProperties() {
        List<Property> properties = propertyService.listProperties();
        return ResponseEntity.ok(properties);
    }

    @PostMapping
    public ResponseEntity<Property> addProperty(@RequestBody Property property) {
        Property newProperty = propertyService.addProperty(property);
        return ResponseEntity.ok(newProperty);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Property> updateProperty(@PathVariable Long id, @RequestBody Property property) {
        property.setId(id);
        Property updatedProperty = propertyService.updateProperty(property);
        return ResponseEntity.ok(updatedProperty);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Property> getPropertyById(@PathVariable Long id) {
        Property property = propertyService.findPropertyById(id);
        return ResponseEntity.ok(property);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
        boolean isDeleted = propertyService.deleteProperty(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
