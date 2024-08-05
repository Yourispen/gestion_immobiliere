package com.javaproject.gestion_immobiliere.repositories;

import com.javaproject.gestion_immobiliere.entities.House;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepository extends JpaRepository<House, Long> {
}
