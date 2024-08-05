package com.javaproject.gestion_immobiliere.controllers;

import com.javaproject.gestion_immobiliere.dto.HouseDTO;
import com.javaproject.gestion_immobiliere.entities.House;
import com.javaproject.gestion_immobiliere.repositories.HouseRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/houses")
public class HouseController {

    @Autowired
    private HouseRepository houseRepository;

    @GetMapping
    @Transactional(readOnly = true)
    public List<HouseDTO> getAllHouses() {
        return houseRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<HouseDTO> getHouseById(@PathVariable Long id) {
        Optional<House> house = houseRepository.findById(id);
        return house.map(value -> ResponseEntity.ok(convertToDTO(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<HouseDTO> createHouse(@RequestBody House house) {
        House savedHouse = houseRepository.save(house);
        HouseDTO dto = convertToDTO(savedHouse);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HouseDTO> updateHouse(@PathVariable Long id, @RequestBody House houseDetails) {
        Optional<House> optionalHouse = houseRepository.findById(id);

        if (optionalHouse.isPresent()) {
            House house = optionalHouse.get();
            house.setDescription(houseDetails.getDescription());
            house.setArea(houseDetails.getArea());
            house.setLocation(houseDetails.getLocation());
            house.setRate(houseDetails.getRate());
            house.setMainImageUrl(houseDetails.getMainImageUrl());
            house.setRegion(houseDetails.getRegion());
            house.setState(houseDetails.getState());
            house.setStatus(houseDetails.getStatus());
            house.setImages(houseDetails.getImages());
            house.setOwner(houseDetails.getOwner());
            house.setNumberOfRooms(houseDetails.getNumberOfRooms());
            house.setNumberOfBathrooms(houseDetails.getNumberOfBathrooms());
            house.setNumberOfKitchens(houseDetails.getNumberOfKitchens());
            house.setNumberOfToilets(houseDetails.getNumberOfToilets());
            house.setNumberOfFloors(houseDetails.getNumberOfFloors());

            House updatedHouse = houseRepository.save(house);
            HouseDTO dto = convertToDTO(updatedHouse);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHouse(@PathVariable Long id) {
        Optional<House> house = houseRepository.findById(id);
        if (house.isPresent()) {
            houseRepository.delete(house.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private HouseDTO convertToDTO(House house) {
        Hibernate.initialize(house.getImages()); // Initialiser explicitement les images
        HouseDTO dto = new HouseDTO();
        dto.setId(house.getId());
        dto.setDescription(house.getDescription());
        dto.setArea(house.getArea());
        dto.setLocation(house.getLocation());
        dto.setRate(house.getRate());
        dto.setMainImageUrl(house.getMainImageUrl());
        dto.setRegion(house.getRegion());
        dto.setState(house.getState().name());
        dto.setStatus(house.getStatus().name());
        dto.setImages(house.getImages());
        dto.setOwnerId(house.getOwner().getId());
        dto.setCreatedAt(house.getCreatedAt());
        dto.setUpdatedAt(house.getUpdatedAt());
        dto.setNumberOfRooms(house.getNumberOfRooms());
        dto.setNumberOfBathrooms(house.getNumberOfBathrooms());
        dto.setNumberOfKitchens(house.getNumberOfKitchens());
        dto.setNumberOfToilets(house.getNumberOfToilets());
        dto.setNumberOfFloors(house.getNumberOfFloors());
        return dto;
    }
}
