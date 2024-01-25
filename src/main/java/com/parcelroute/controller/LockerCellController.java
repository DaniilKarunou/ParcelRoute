package com.parcelroute.controller;

import com.parcelroute.model.Locker;
import com.parcelroute.model.LockerCell;
import com.parcelroute.dto.LockerCellRequest;
import com.parcelroute.model.parcel.Size;
import com.parcelroute.service.CellService;
import com.parcelroute.service.LockerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@RestController
@RequestMapping("/api/lockercells")
public class LockerCellController {

    private final LockerService lockerService;
    private final CellService lockerCellService;

    public LockerCellController(LockerService lockerService, CellService lockerCellService) {
        this.lockerService = lockerService;
        this.lockerCellService = lockerCellService;
    }

    @GetMapping("/")
    @Operation(summary = "Get all locker cells", description = "Retrieve a list of all locker cells")
    public ResponseEntity<List<LockerCell>> getAllLockerCells() {
        List<LockerCell> lockerCells = lockerCellService.getAllCells();
        return ResponseEntity.ok(lockerCells);
    }

    @PostMapping("/add-lockercell")
    @Operation(summary = "Add a locker cell", description = "Add a new locker cell")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Locker cell successfully added"),
            @ApiResponse(responseCode = "400", description = "Locker cell could not be added", content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<String> addLockerCell(@RequestBody LockerCellRequest lockerCellRequest) {
        Long lockerId = lockerCellRequest.getLockerId();
        Size cellSize = lockerCellRequest.getCellSize();

        // Handle the creation of a new LockerCell based on the request data.
        Locker cellLocker = lockerService.getLockerById(lockerId);
        LockerCell lockerCell = new LockerCell(cellLocker, cellSize);
        // Set other attributes for the LockerCell entity as needed.
        lockerCellService.addCell(lockerCell);
        return ResponseEntity.ok("Locker cell successfully added");
    }
}
