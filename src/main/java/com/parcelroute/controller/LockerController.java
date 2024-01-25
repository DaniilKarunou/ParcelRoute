package com.parcelroute.controller;

import com.parcelroute.dto.LockerRequest;
import com.parcelroute.model.Locker;
import com.parcelroute.service.LockerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lockers")
public class LockerController {

    private final LockerService lockerService;

    public LockerController(LockerService lockerService) {
        this.lockerService = lockerService;
    }

    @GetMapping("/")
    @Operation(summary = "Get all lockers", description = "Retrieve a list of all lockers")
    public ResponseEntity<List<Locker>> getAllLockers() {
        List<Locker> lockers = lockerService.getAllLockers();
        return ResponseEntity.ok(lockers);
    }

    @PostMapping("/add-locker")
    @Operation(summary = "Add a locker", description = "Add a new locker")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Locker successfully added"),
            @ApiResponse(responseCode = "400", description = "Locker could not be added", content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<String> addLocker(@RequestBody LockerRequest lockerRequest) {
        String address = lockerRequest.getAddress();
        // Assuming you have other attributes for the Locker entity.

        Locker locker = new Locker(address);
        // Set other attributes for the Locker entity as needed.

        lockerService.addLocker(locker);
        return ResponseEntity.ok("Locker successfully added");
    }
}