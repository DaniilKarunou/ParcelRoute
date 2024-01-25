package com.parcelroute.repository;

import com.parcelroute.model.Locker;
import com.parcelroute.model.LockerCell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing Locker entities.
 */
@Repository
public interface LockerRepository extends JpaRepository<Locker, Long> {

    /**
     * Find cells by locker ID.
     *
     * @param locker_id the ID of the locker
     * @return a list of cells belonging to the specified locker
     */
    @Query("SELECT lc FROM LockerCell lc WHERE lc.locker = :locker_id")
    List<LockerCell> findCellsByLockerID(@Param("locker_id") Long locker_id);

}

