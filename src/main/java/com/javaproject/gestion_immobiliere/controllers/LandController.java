package com.javaproject.gestion_immobiliere.controllers;

import com.javaproject.gestion_immobiliere.dto.LandDTO;
import com.javaproject.gestion_immobiliere.entities.Land;
import com.javaproject.gestion_immobiliere.repositories.LandRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/lands")
public class LandController {

    @Autowired
    private LandRepository landRepository;

    @GetMapping
    @Transactional(readOnly = true)
    public List<LandDTO> getAllLands() {
        return landRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<LandDTO> getLandById(@PathVariable Long id) {
        Optional<Land> land = landRepository.findById(id);
        return land.map(value -> ResponseEntity.ok(convertToDTO(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<LandDTO> createLand(@RequestBody Land land) {
        Land savedLand = landRepository.save(land);
        LandDTO dto = convertToDTO(savedLand);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LandDTO> updateLand(@PathVariable Long id, @RequestBody Land landDetails) {
        Optional<Land> optionalLand = landRepository.findById(id);

        if (optionalLand.isPresent()) {
            Land land = optionalLand.get();
            land.setDescription(landDetails.getDescription());
            land.setArea(landDetails.getArea());
            land.setLocation(landDetails.getLocation());
            land.setRate(landDetails.getRate());
            land.setMainImageUrl(landDetails.getMainImageUrl());
            land.setRegion(landDetails.getRegion());
            land.setState(landDetails.getState());
            land.setStatus(landDetails.getStatus());
            land.setImages(landDetails.getImages());
            land.setOwner(landDetails.getOwner());
            land.setLandType(landDetails.getLandType());

            Land updatedLand = landRepository.save(land);
            LandDTO dto = convertToDTO(updatedLand);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLand(@PathVariable Long id) {
        Optional<Land> land = landRepository.findById(id);
        if (land.isPresent()) {
            landRepository.delete(land.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private LandDTO convertToDTO(Land land) {
        Hibernate.initialize(land.getImages()); // Initialiser explicitement les images
        LandDTO dto = new LandDTO();
        dto.setId(land.getId());
        dto.setDescription(land.getDescription());
        dto.setArea(land.getArea());
        dto.setLocation(land.getLocation());
        dto.setRate(land.getRate());
        dto.setMainImageUrl(land.getMainImageUrl());
        dto.setRegion(land.getRegion());
        dto.setState(land.getState().name());
        dto.setStatus(land.getStatus().name());
        dto.setImages(land.getImages());
        dto.setOwnerId(land.getOwner().getId());
        dto.setCreatedAt(land.getCreatedAt());
        dto.setUpdatedAt(land.getUpdatedAt());
        dto.setLandType(land.getLandType());
        return dto;
    }
}
