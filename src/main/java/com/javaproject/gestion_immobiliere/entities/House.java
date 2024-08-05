package com.javaproject.gestion_immobiliere.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
//@DiscriminatorValue("HOUSE")
@Table(name = "houses")
public class House extends AbstractAccommodation {

    @Setter
    @Getter
    private int numberOfFloors;

}