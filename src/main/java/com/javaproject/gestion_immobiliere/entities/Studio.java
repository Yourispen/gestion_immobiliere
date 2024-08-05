package com.javaproject.gestion_immobiliere.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
//@DiscriminatorValue("STUDIO")
@Table(name = "studios")
public class Studio extends AbstractAccommodation {

    @Setter
    @Getter
    private String studioNumber;

}