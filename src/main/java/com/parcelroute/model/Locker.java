package com.parcelroute.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Entity class for managing Locker entities.
 */
@Entity
@Table(name = "lockers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Locker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "The unique ID of the locker")
    private Long Id;

    @OneToMany(mappedBy = "locker")
    @JsonIgnore
    private List<LockerCell> cells;

    @Column(nullable = false)
    @Schema(description = "The address of the locker")
    private String address;

    public Locker(String address) {
        this.address = address;
    }

}

