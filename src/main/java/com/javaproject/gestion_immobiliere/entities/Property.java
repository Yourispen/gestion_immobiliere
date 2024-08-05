package com.javaproject.gestion_immobiliere.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "properties")
@Inheritance(strategy = InheritanceType.JOINED)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public abstract class Property extends AbstractCreation {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Setter
    @Getter
    @Column(nullable = false)
    private String description;

    @Setter
    @Getter
    @Column(nullable = false)
    private double area;

    @Setter
    @Getter
    @Column(nullable = false)
    private String location;

    @Setter
    @Getter
    @Column(nullable = false)
    private double rate;

    @Setter
    @Getter
    @Column(name = "main_image_url", nullable = false)
    private String mainImageUrl;

    @Setter
    @Getter
    @Column(nullable = false)
    private String region;

    @Setter
    @Getter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PropertyState state;

    @Setter
    @Getter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PropertyStatus status;

    @Setter
    @Getter
    @ElementCollection
    @CollectionTable(name = "property_images", joinColumns = @JoinColumn(name = "property_id"))
    @Column(name = "image_urls")
    private List<String> images;

    @Setter
    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;
}
