package com.parcelroute.service;

import com.parcelroute.exception.InvalidPickupCodeException;
import com.parcelroute.exception.ParcelAlreadyCollectedException;
import com.parcelroute.model.LockerCell;
import com.parcelroute.model.parcel.Parcel;
import com.parcelroute.repository.ParcelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Random;

/**
 * Service class for managing parcels and shipments.
 */
@Service
public class ParcelService {

    private final ParcelRepository parcelRepository;
    private final CellService cellService;
    private final EmailService emailService;

    @Autowired
    public ParcelService(ParcelRepository parcelRepository,
                         CellService cellService,
                         EmailService emailService) {
        this.parcelRepository = parcelRepository;
        this.cellService = cellService;
        this.emailService = emailService;
    }

    /**
     * Retrieve all parcels.
     *
     * @return List of parcels
     */
    public List<Parcel> getAllParcels() {
        return parcelRepository.findAll();
    }

    /**
     * Create a shipment for the given parcel.
     *
     * @param parcel Parcel to be shipped
     */
    public void createShipment(Parcel parcel){
        parcelRepository.save(parcel);
    }

    /**
     * Complete a shipment by assigning a locker cell, generating a pickup code, and sending an email notification.
     *
     * @param parcelId ID of the parcel to be marked as completed
     */
    public void completeShipment(Long parcelId){
        Parcel parcel = parcelRepository.findById(parcelId).orElseThrow();
        String pickupCode = generateRandomCode();

        // send code via email
        emailService.sendEmail(parcel.getRecipient().getEmail(),
                "Parcel pickup code", "Your parcel pickup code is: " + pickupCode);

        LockerCell assignedCell = cellService.findAvailableCellByLockerIdAndSize(parcel.getLockerId(), parcel.getSize());

        parcel.setCellId(assignedCell.getId());
        parcel.setPickupCode(pickupCode);
        assignedCell.setAvailable(false);
        cellService.save(assignedCell);
        parcelRepository.save(parcel);
    }

    /**
     * Pick up a parcel using the provided pickup code.
     *
     * @param id   ID of the parcel to be picked up
     * @param code Pickup code
     * @throws InvalidPickupCodeException if the provided pickup code is invalid
     * @throws ParcelAlreadyCollectedException if the parcel has already been collected
     */
    public void pickUpParcel(Long id, String code){
        Parcel parcel = parcelRepository.findById(id).orElseThrow();
        LockerCell cell = cellService.findCellById(parcel.getCellId());

        if (parcel.isCollected()) {
            throw new ParcelAlreadyCollectedException("Parcel already collected");
        }

        if(parcel.getPickupCode().equals(code)){
            parcel.setCellId(null);
            parcel.setLockerId(null);
            parcel.setCollected(true);
            parcel.setPickupCode(null);
            cell.setAvailable(true);
            parcelRepository.save(parcel);
        }
        else{
            throw new InvalidPickupCodeException("Invalid pickup code");
        }
    }

    /**
     * Generate a random pickup code.
     *
     * @return Random pickup code
     */
    private String generateRandomCode() {
        Random random = new Random();
        int min = 100_000; // Minimum 6-digit number
        int max = 999_999; // Maximum 6-digit number
        int randomNumber = random.nextInt(max - min + 1) + min;
        return String.valueOf(randomNumber);
    }
}

