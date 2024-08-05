package com.javaproject.gestion_immobiliere.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
public abstract class AbstractAccommodation extends Property {

    @Setter
    @Getter
    @Column(nullable = false)
    private int numberOfRooms;

    @Setter
    @Getter
    @Column(nullable = false)
    private int numberOfBathrooms;

    @Setter
    @Getter
    @Column(nullable = false)
    private int numberOfKitchens;

    @Setter
    @Getter
    @Column(nullable = false)
    private int numberOfToilets;

}
