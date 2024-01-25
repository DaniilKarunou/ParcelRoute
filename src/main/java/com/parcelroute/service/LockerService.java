package com.parcelroute.service;

import com.parcelroute.model.Locker;
import com.parcelroute.repository.LockerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing lockers.
 */
@Service
public class LockerService {

    private final LockerRepository lockerRepository;

    @Autowired
    public LockerService(LockerRepository lockerRepository) {
        this.lockerRepository = lockerRepository;
    }

    public Locker getLockerById(Long lockerId) {
        return lockerRepository.findById(lockerId).orElse(null);
    }

    public void addLocker(Locker locker) {
        lockerRepository.save(locker);
    }

    public List<Locker> getAllLockers() {
        return lockerRepository.findAll();
    }
}

