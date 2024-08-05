package com.javaproject.gestion_immobiliere.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
//@DiscriminatorValue("APARTMENT")
@Table(name = "apartments")
public class Apartment extends AbstractAccommodation {

    @Setter
    @Getter
    private String apartmentNumber;


    @Override
    public String toString() {
        return "Apartment{" +
                "id=" + getId() +
                ", description='" + getDescription() + '\'' +
                ", area=" + getArea() +
                ", location='" + getLocation() + '\'' +
                ", rate=" + getRate() +
                ", mainImageUrl='" + getMainImageUrl() + '\'' +
                ", region='" + getRegion() + '\'' +
                ", state=" + getState() +
                ", status=" + getStatus() +
                ", numberOfRooms=" + getNumberOfRooms() +
                ", numberOfBathrooms=" + getNumberOfBathrooms() +
                ", numberOfKitchens=" + getNumberOfKitchens() +
                ", numberOfToilets=" + getNumberOfToilets() +
                ", apartmentNumber='" + apartmentNumber + '\'' +
                ", owner=" + (getOwner() != null ? getOwner().getId() : "null") +
                ", images=" + getImages() +
                '}';
    }
}