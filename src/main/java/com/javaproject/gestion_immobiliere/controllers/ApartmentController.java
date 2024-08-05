package com.javaproject.gestion_immobiliere.controllers;

import com.javaproject.gestion_immobiliere.dto.ApartmentDTO;
import com.javaproject.gestion_immobiliere.entities.Apartment;
import com.javaproject.gestion_immobiliere.repositories.ApartmentRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/apartments")
public class ApartmentController {

    @Autowired
    private ApartmentRepository apartmentRepository;

    @GetMapping
    @Transactional(readOnly = true)
    public List<ApartmentDTO> getAllApartments() {
        return apartmentRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<ApartmentDTO> getApartmentById(@PathVariable Long id) {
        Optional<Apartment> apartment = apartmentRepository.findById(id);
        return apartment.map(value -> ResponseEntity.ok(convertToDTO(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ApartmentDTO> createApartment(@RequestBody Apartment apartment) {
        // Afficher les détails de l'appartement dans la console
        //System.out.println("Apartment details before saving: " + apartment);
        Apartment savedApartment = apartmentRepository.save(apartment);
        ApartmentDTO dto = convertToDTO(savedApartment);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApartmentDTO> updateApartment(@PathVariable Long id, @RequestBody Apartment apartmentDetails) {
        Optional<Apartment> optionalApartment = apartmentRepository.findById(id);

        if (optionalApartment.isPresent()) {
            Apartment apartment = optionalApartment.get();
            apartment.setDescription(apartmentDetails.getDescription());
            apartment.setArea(apartmentDetails.getArea());
            apartment.setLocation(apartmentDetails.getLocation());
            apartment.setRate(apartmentDetails.getRate());
            apartment.setMainImageUrl(apartmentDetails.getMainImageUrl());
            apartment.setRegion(apartmentDetails.getRegion());
            apartment.setState(apartmentDetails.getState());
            apartment.setStatus(apartmentDetails.getStatus());
            apartment.setImages(apartmentDetails.getImages()); // Ajouter les images
            apartment.setOwner(apartmentDetails.getOwner());
            apartment.setNumberOfRooms(apartmentDetails.getNumberOfRooms());
            apartment.setNumberOfBathrooms(apartmentDetails.getNumberOfBathrooms());
            apartment.setNumberOfKitchens(apartmentDetails.getNumberOfKitchens());
            apartment.setNumberOfToilets(apartmentDetails.getNumberOfToilets());
            apartment.setApartmentNumber(apartmentDetails.getApartmentNumber());

            Apartment updatedApartment = apartmentRepository.save(apartment);
            ApartmentDTO dto = convertToDTO(updatedApartment);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApartment(@PathVariable Long id) {
        Optional<Apartment> apartment = apartmentRepository.findById(id);
        if (apartment.isPresent()) {
            apartmentRepository.delete(apartment.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private ApartmentDTO convertToDTO(Apartment apartment) {
        Hibernate.initialize(apartment.getImages()); // Initialiser explicitement les images
        ApartmentDTO dto = new ApartmentDTO();
        dto.setId(apartment.getId());
        dto.setDescription(apartment.getDescription());
        dto.setArea(apartment.getArea());
        dto.setLocation(apartment.getLocation());
        dto.setRate(apartment.getRate());
        dto.setMainImageUrl(apartment.getMainImageUrl());
        dto.setRegion(apartment.getRegion());
        dto.setState(apartment.getState().name());
        dto.setStatus(apartment.getStatus().name());
        dto.setImages(apartment.getImages()); // Assurez-vous que les images sont chargées
        dto.setOwnerId(apartment.getOwner().getId());
        dto.setCreatedAt(apartment.getCreatedAt());
        dto.setUpdatedAt(apartment.getUpdatedAt());
        dto.setNumberOfRooms(apartment.getNumberOfRooms());
        dto.setNumberOfBathrooms(apartment.getNumberOfBathrooms());
        dto.setNumberOfKitchens(apartment.getNumberOfKitchens());
        dto.setNumberOfToilets(apartment.getNumberOfToilets());
        dto.setApartmentNumber(apartment.getApartmentNumber());
        return dto;
    }
}
