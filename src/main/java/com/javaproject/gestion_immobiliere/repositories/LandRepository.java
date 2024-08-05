package com.javaproject.gestion_immobiliere.repositories;

import com.javaproject.gestion_immobiliere.entities.Land;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LandRepository extends JpaRepository<Land, Long> {
}
