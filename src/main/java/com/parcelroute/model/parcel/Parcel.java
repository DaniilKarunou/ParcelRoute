package com.parcelroute.model.parcel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.parcelroute.model.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

/**
 * Represents a parcel stored in the system.
 */
@Entity
@Table(name = "parcels")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Represents a parcel entity.")
public class Parcel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "The unique ID of the parcel")
    private Long id;

    @Schema(description = "The ID of the locker where the parcel is stored")
    private Long lockerId;

    @Schema(description = "The ID of the cell where the parcel is stored")
    private Long cellId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Schema(description = "The size of the parcel")
    private Size size;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Schema(description = "The type of the parcel")
    private ParcelType parcelType;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @Schema(description = "The sender of the parcel")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @Schema(description = "The recipient of the parcel")
    private User recipient;

    @Column
    @Schema(description = "The pickup code of the parcel")
    private String pickupCode;

    @Schema(description = "Indicates if the parcel has been collected")
    private boolean isCollected;

    public Parcel(Long lockerId, Size size, ParcelType parcelType, User sender, User recipient) {
        this.lockerId = lockerId;
        this.size = size;
        this.parcelType = parcelType;
        this.sender = sender;
        this.recipient = recipient;
        this.isCollected = false;
    }
}

