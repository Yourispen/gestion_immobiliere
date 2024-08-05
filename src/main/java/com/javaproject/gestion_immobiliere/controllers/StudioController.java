package com.javaproject.gestion_immobiliere.controllers;

import com.javaproject.gestion_immobiliere.dto.StudioDTO;
import com.javaproject.gestion_immobiliere.entities.Studio;
import com.javaproject.gestion_immobiliere.repositories.StudioRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/studios")
public class StudioController {

    @Autowired
    private StudioRepository studioRepository;

    @GetMapping
    @Transactional(readOnly = true)
    public List<StudioDTO> getAllStudios() {
        return studioRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<StudioDTO> getStudioById(@PathVariable Long id) {
        Optional<Studio> studio = studioRepository.findById(id);
        return studio.map(value -> ResponseEntity.ok(convertToDTO(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<StudioDTO> createStudio(@RequestBody Studio studio) {
        Studio savedStudio = studioRepository.save(studio);
        StudioDTO dto = convertToDTO(savedStudio);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudioDTO> updateStudio(@PathVariable Long id, @RequestBody Studio studioDetails) {
        Optional<Studio> optionalStudio = studioRepository.findById(id);

        if (optionalStudio.isPresent()) {
            Studio studio = optionalStudio.get();
            studio.setDescription(studioDetails.getDescription());
            studio.setArea(studioDetails.getArea());
            studio.setLocation(studioDetails.getLocation());
            studio.setRate(studioDetails.getRate());
            studio.setMainImageUrl(studioDetails.getMainImageUrl());
            studio.setRegion(studioDetails.getRegion());
            studio.setState(studioDetails.getState());
            studio.setStatus(studioDetails.getStatus());
            studio.setImages(studioDetails.getImages());
            studio.setOwner(studioDetails.getOwner());
            studio.setNumberOfRooms(studioDetails.getNumberOfRooms());
            studio.setNumberOfBathrooms(studioDetails.getNumberOfBathrooms());
            studio.setNumberOfKitchens(studioDetails.getNumberOfKitchens());
            studio.setNumberOfToilets(studioDetails.getNumberOfToilets());
            studio.setStudioNumber(studioDetails.getStudioNumber());

            Studio updatedStudio = studioRepository.save(studio);
            StudioDTO dto = convertToDTO(updatedStudio);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudio(@PathVariable Long id) {
        Optional<Studio> studio = studioRepository.findById(id);
        if (studio.isPresent()) {
            studioRepository.delete(studio.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private StudioDTO convertToDTO(Studio studio) {
        Hibernate.initialize(studio.getImages()); // Initialiser explicitement les images
        StudioDTO dto = new StudioDTO();
        dto.setId(studio.getId());
        dto.setDescription(studio.getDescription());
        dto.setArea(studio.getArea());
        dto.setLocation(studio.getLocation());
        dto.setRate(studio.getRate());
        dto.setMainImageUrl(studio.getMainImageUrl());
        dto.setRegion(studio.getRegion());
        dto.setState(studio.getState().name());
        dto.setStatus(studio.getStatus().name());
        dto.setImages(studio.getImages());
        dto.setOwnerId(studio.getOwner().getId());
        dto.setCreatedAt(studio.getCreatedAt());
        dto.setUpdatedAt(studio.getUpdatedAt());
        dto.setNumberOfRooms(studio.getNumberOfRooms());
        dto.setNumberOfBathrooms(studio.getNumberOfBathrooms());
        dto.setNumberOfKitchens(studio.getNumberOfKitchens());
        dto.setNumberOfToilets(studio.getNumberOfToilets());
        dto.setStudioNumber(studio.getStudioNumber());
        return dto;
    }
}
