package com.javaproject.gestion_immobiliere.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;

    @Setter
    @Getter
    @Column(unique = true, length = 100, nullable = false)
    private String email;

    @Setter
    @Column(nullable = false)
    private String password;

    @Setter
    @Getter
    @Column(nullable = false)
    private String firstName;

    @Setter
    @Getter
    @Column(nullable = false)
    private String lastName;

    @Setter
    @Getter
    @Column(nullable = false)
    private LocalDate birthDate;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ADMIN"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
