package com.javaproject.gestion_immobiliere.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "owners")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Owner extends AbstractCreation {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Setter
    @Getter
    @Column(nullable = false)
    private String lastName;

    @Setter
    @Getter
    @Column(nullable = false)
    private String firstName;

    @Setter
    @Getter
    @Column(nullable = false)
    private String phoneNumber;

    @Setter
    @Getter
    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    private Set<Property> properties;
}
