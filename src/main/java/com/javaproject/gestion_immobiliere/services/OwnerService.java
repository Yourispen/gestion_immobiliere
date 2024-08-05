package com.javaproject.gestion_immobiliere.services;

import com.javaproject.gestion_immobiliere.entities.Owner;
import com.javaproject.gestion_immobiliere.repositories.OwnerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerService implements IOwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Override
    public List<Owner> listOwners() {
        return ownerRepository.findAll();
    }

    @Override
    public Owner addOwner(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public Owner updateOwner(Owner owner) {
        if (owner.getId() == null) {
            throw new IllegalArgumentException("Owner ID cannot be null for update operation");
        }
        if (!ownerRepository.existsById(owner.getId())) {
            throw new EntityNotFoundException("Owner with ID " + owner.getId() + " does not exist");
        }
        return ownerRepository.save(owner);
    }

    @Override
    public Owner findOwnerById(Long id) {
        return ownerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Owner with ID " + id + " not found"));
    }

    @Override
    public boolean deleteOwner(Long id) {
        if (ownerRepository.existsById(id)) {
            ownerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
