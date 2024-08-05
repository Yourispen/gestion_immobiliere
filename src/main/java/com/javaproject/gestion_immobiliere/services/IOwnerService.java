package com.javaproject.gestion_immobiliere.services;

import com.javaproject.gestion_immobiliere.entities.Owner;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IOwnerService {
    List<Owner> listOwners();
    Owner addOwner(Owner owner);
    Owner updateOwner(Owner owner);
    Owner findOwnerById(Long id);
    boolean deleteOwner(Long id);
}
