package com.javaproject.gestion_immobiliere.repositories;

import com.javaproject.gestion_immobiliere.entities.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Long> {
}
