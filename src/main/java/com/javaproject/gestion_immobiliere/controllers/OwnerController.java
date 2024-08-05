package com.javaproject.gestion_immobiliere.controllers;

import com.javaproject.gestion_immobiliere.dto.OwnerDTO;
import com.javaproject.gestion_immobiliere.entities.Owner;
import com.javaproject.gestion_immobiliere.repositories.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/owners")
public class OwnerController {

    @Autowired
    private OwnerRepository ownerRepository;

    @GetMapping
    public List<OwnerDTO> getAllOwners() {
        return ownerRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerDTO> getOwnerById(@PathVariable Long id) {
        Optional<Owner> owner = ownerRepository.findById(id);
        return owner.map(value -> ResponseEntity.ok(convertToDTO(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<OwnerDTO> createOwner(@RequestBody OwnerDTO ownerDTO) {
        Owner owner = convertToEntity(ownerDTO);
        Owner savedOwner = ownerRepository.save(owner);
        return ResponseEntity.ok(convertToDTO(savedOwner));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OwnerDTO> updateOwner(@PathVariable Long id, @RequestBody OwnerDTO ownerDTO) {
        Optional<Owner> optionalOwner = ownerRepository.findById(id);

        if (optionalOwner.isPresent()) {
            Owner owner = optionalOwner.get();
            owner.setLastName(ownerDTO.getLastName());
            owner.setFirstName(ownerDTO.getFirstName());
            owner.setPhoneNumber(ownerDTO.getPhoneNumber());
            Owner updatedOwner = ownerRepository.save(owner);
            return ResponseEntity.ok(convertToDTO(updatedOwner));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable Long id) {
        Optional<Owner> owner = ownerRepository.findById(id);
        if (owner.isPresent()) {
            ownerRepository.delete(owner.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private OwnerDTO convertToDTO(Owner owner) {
        OwnerDTO dto = new OwnerDTO();
        dto.setId(owner.getId());
        dto.setLastName(owner.getLastName());
        dto.setFirstName(owner.getFirstName());
        dto.setPhoneNumber(owner.getPhoneNumber());
        return dto;
    }

    private Owner convertToEntity(OwnerDTO ownerDTO) {
        Owner owner = new Owner();
        owner.setId(ownerDTO.getId());
        owner.setLastName(ownerDTO.getLastName());
        owner.setFirstName(ownerDTO.getFirstName());
        owner.setPhoneNumber(ownerDTO.getPhoneNumber());
        return owner;
    }
}
