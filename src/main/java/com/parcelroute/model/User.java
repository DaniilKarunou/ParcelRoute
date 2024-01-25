package com.parcelroute.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.parcelroute.model.parcel.Parcel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Represents a user in the system.
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @OneToMany(mappedBy = "sender")
    @JsonIgnore
    private List<Parcel> sentParcels;

    @OneToMany(mappedBy = "recipient")
    @JsonIgnore
    private List<Parcel> receivedParcels;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}