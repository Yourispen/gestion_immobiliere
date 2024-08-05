package com.javaproject.gestion_immobiliere.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@MappedSuperclass
public abstract class AbstractCreation {

    @Setter
    @Getter
    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @Setter
    @Getter
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;
}
