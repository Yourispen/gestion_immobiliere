package com.javaproject.gestion_immobiliere.repositories;

import com.javaproject.gestion_immobiliere.entities.AbstractAccommodation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccommodationRepository extends JpaRepository<AbstractAccommodation, Long> {
}
