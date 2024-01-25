package com.parcelroute.repository;

import com.parcelroute.model.LockerCell;
import com.parcelroute.model.parcel.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Repository interface for managing LockerCell entities.
 */
public interface CellRepository extends JpaRepository<LockerCell, Long> {

    /**
     * Find available cells by size.
     *
     * @param size the size of the cell
     * @return a list of available cells with the specified size
     */
    @Query("SELECT lc FROM LockerCell lc WHERE lc.isAvailable = true AND lc.cell_size = :size")
    List<LockerCell> findAvailableCellsBySize(Size size);

    @Query("SELECT lc FROM LockerCell lc WHERE lc.isAvailable = true AND lc.locker.Id = :lockerId AND lc.cell_size = :size")
    List<LockerCell> findAvailableCellsByLockerIdAndSize(Long lockerId, Size size);
}