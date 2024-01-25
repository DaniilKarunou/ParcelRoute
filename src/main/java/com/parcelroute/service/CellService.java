package com.parcelroute.service;

import com.parcelroute.model.LockerCell;
import com.parcelroute.model.parcel.Size;
import com.parcelroute.repository.CellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing LockerCell entities.
 */
@Service
public class CellService {

    private final CellRepository cellRepository;

    @Autowired
    public CellService(CellRepository cellRepository){
        this.cellRepository = cellRepository;
    }

    /**
     * Save a locker cell.
     *
     * @param lockerCell the locker cell to be saved
     */
    public void save(LockerCell lockerCell){
        cellRepository.save(lockerCell);
    }

    /**
     * Find the first available locker cell of the specified size.
     *
     * @param size the size of the locker cell to find
     * @return the first available locker cell of the specified size
     */
    public LockerCell findFirstAvailableCell(Size size){
        List<LockerCell> availableCells = cellRepository.findAvailableCellsBySize(size);
        if (!availableCells.isEmpty()) {
            return availableCells.get(0);
        }
        return null; // No available cells of the specified size
    }

    /**
     * Find the first available locker cell of the specified size.
     *
     * @param lockerId the id of the locker
     * @param size the size of the locker cell to find
     * @return the first available locker cell of the specified size
     */
    public LockerCell findAvailableCellByLockerIdAndSize(Long lockerId, Size size){
        List<LockerCell> availableCells = cellRepository.findAvailableCellsByLockerIdAndSize(lockerId, size);
        if (!availableCells.isEmpty()) {
            return availableCells.get(0);
        }
        return null;
    }

    public LockerCell findCellById(Long id){
        return cellRepository.findById(id).orElseThrow();
    }

    public List<LockerCell> getAllCells(){
        return cellRepository.findAll();
    }

    public void addCell(LockerCell cell){
        cellRepository.save(cell);
    }
}
