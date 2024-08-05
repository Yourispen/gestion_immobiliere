package com.javaproject.gestion_immobiliere.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class StudioDTO {
    private Long id;
    private String description;
    private double area;
    private String location;
    private double rate;
    private String mainImageUrl;
    private String region;
    private String state;
    private String status;
    private List<String> images;
    private Long ownerId;
    private Date createdAt;
    private Date updatedAt;
    private int numberOfRooms;
    private int numberOfBathrooms;
    private int numberOfKitchens;
    private int numberOfToilets;
    private String studioNumber;
}
