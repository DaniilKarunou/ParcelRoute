package com.parcelroute.model;

import com.parcelroute.model.parcel.Size;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity class for managing LockerCell entities.
 */
@Entity
@Table(name = "locker_cells")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Represents a locker cell belonging to a locker.")
public class LockerCell {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "The unique ID of the locker cell")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "locker_id")
    @Schema(description = "The locker that this cell belongs to")
    private Locker locker;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Schema(description = "The size of the cell", required = true)
    private Size cell_size;

    @Schema(description = "Indicates if the cell is available")
    private boolean isAvailable = true;

    public LockerCell(Locker locker, Size cell_size) {
        this.locker = locker;
        this.cell_size = cell_size;
    }
}