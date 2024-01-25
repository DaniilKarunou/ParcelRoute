package com.parcelroute.controller;

import com.parcelroute.dto.ParcelRequest;
import com.parcelroute.exception.InvalidPickupCodeException;
import com.parcelroute.model.User;
import com.parcelroute.model.parcel.Parcel;
import com.parcelroute.service.CellService;
import com.parcelroute.service.LockerService;
import com.parcelroute.service.ParcelService;
import com.parcelroute.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parcels")
public class ParcelController {

    private final LockerService lockerService;
    private final ParcelService parcelService;
    private final CellService cellService;
    private final UserService userService;

    @Autowired
    public ParcelController(LockerService lockerService, ParcelService parcelService, CellService cellService, UserService userService) {
        this.lockerService = lockerService;
        this.parcelService = parcelService;
        this.cellService = cellService;
        this.userService = userService;
    }

    @GetMapping("/")
    @Operation(summary = "Get all parcels", description = "Retrieve a list of all parcels")
    public ResponseEntity<List<Parcel>> getAllParcels() {
        return ResponseEntity.ok(parcelService.getAllParcels());
    }

    @PostMapping("/send-parcel")
    @Operation(summary = "Send a parcel", description = "Send a new parcel")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Parcel successfully sent"),
            @ApiResponse(responseCode = "400", description = "Parcel could not be sent", content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<String> sendParcel(@RequestBody ParcelRequest userRequest) {
        try {
            String senderEmail = userRequest.getSenderEmail();
            String recipientEmail = userRequest.getRecipientEmail();

            User sender = userService.findUserByEmail(senderEmail);
            User recipient = userService.findUserByEmail(recipientEmail);
            Parcel parcel = new Parcel(
                    userRequest.getLockerId(),
                    userRequest.getSize(),
                    userRequest.getParcelType(),
                    sender,
                    recipient
            );
            parcelService.createShipment(parcel);
            return ResponseEntity.ok("Parcel successfully sent");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Parcel could not be sent");
        }
    }

    @PostMapping("/deliver-parcel")
    @Operation(summary = "Deliver a parcel", description = "Deliver a parcel")
    @ApiResponse(responseCode = "200", description = "Parcel successfully delivered")
    @ApiResponse(responseCode = "400", description = "Parcel could not be delivered", content = @Content(schema = @Schema(implementation = String.class)))
    public ResponseEntity<String> deliverParcel(@Parameter(description = "ID of the parcel to be delivered", required = true) @RequestParam Long id) {
        try {
            parcelService.completeShipment(id);
            return ResponseEntity.ok("Parcel successfully delivered");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Parcel could not be delivered");
        }
    }

    @PostMapping("/pickup-parcel")
    @Operation(summary = "Pick up a parcel", description = "Pick up a parcel from a locker")
    @ApiResponse(responseCode = "200", description = "Parcel successfully picked up")
    @ApiResponse(responseCode = "400", description = "Invalid pickup code", content = @Content(schema = @Schema(implementation = String.class)))
    public ResponseEntity<String> pickUpParcel(
            @Parameter(description = "ID of the parcel to be picked up", required = true) @RequestParam Long id,
            @Parameter(description = "Pickup code associated with the parcel", required = true) @RequestParam String code) {
        try {
            parcelService.pickUpParcel(id, code);
            return ResponseEntity.ok("Parcel successfully picked up");
        } catch (InvalidPickupCodeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}