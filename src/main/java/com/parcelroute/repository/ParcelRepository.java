package com.parcelroute.repository;

import com.parcelroute.model.parcel.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Parcel entities.
 */
@Repository
public interface ParcelRepository extends JpaRepository<Parcel, Long> {

    /**
     * Find a parcel by its pickup code.
     *
     * @param pickupCode the pickup code of the parcel
     * @return the parcel with the specified pickup code
     */
    Parcel findByPickupCode(String pickupCode);

    /**
     * Find a parcel by its pickup code and locker ID.
     *
     * @param pickupCode the pickup code of the parcel
     * @param lockerId the ID of the locker associated with the parcel
     * @return the parcel with the specified pickup code and locker ID
     */
    Parcel findByPickupCodeAndLockerId(String pickupCode, Long lockerId);

    /**
     * Find a parcel by its locker ID.
     *
     * @param lockerId the ID of the locker associated with the parcel
     * @return the parcel with the specified locker ID
     */
    Parcel findByLockerId(Long lockerId);
}