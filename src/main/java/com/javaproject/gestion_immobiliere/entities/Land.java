package com.javaproject.gestion_immobiliere.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
//@DiscriminatorValue("LAND")
@Table(name = "lands")
public class Land extends Property {

    @Setter
    @Getter
    private String landType;

}