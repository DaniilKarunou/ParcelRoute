package com.parcelroute.controller;

import com.parcelroute.dto.UserRequest;
import com.parcelroute.model.User;
import com.parcelroute.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    @Operation(summary = "Get all users", description = "Retrieve a list of all users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/add-user")
    @Operation(summary = "Add a user", description = "Add a new user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User successfully added"),
            @ApiResponse(responseCode = "400", description = "User could not be added", content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<String> addUser(@RequestBody UserRequest userRequest) {
        String name = userRequest.getName();
        String email = userRequest.getEmail();

        User user = new User(name, email);
        userService.addUser(user);
        return ResponseEntity.ok("User successfully added");
    }
}
