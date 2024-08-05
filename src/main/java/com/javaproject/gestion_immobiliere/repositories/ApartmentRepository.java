package com.javaproject.gestion_immobiliere.repositories;

import com.javaproject.gestion_immobiliere.entities.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
}
