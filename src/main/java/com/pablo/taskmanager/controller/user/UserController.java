package com.pablo.taskmanager.controller.user;
import com.pablo.taskmanager.model.user.User;
import com.pablo.taskmanager.service.user.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Operation(summary = "Create a new User", description = "This endpoint allows to create a new user in database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Error in the validation of the data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @Operation(summary = "Retrieve all Users", description = "This endpoint retrieves a list of all users from the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of users retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        List <User> user = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @Operation(summary = "Retrieve a User by ID", description = "This endpoint retrieves a specific user from the database based on their unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "User not found with the given ID"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })


    @GetMapping("/user/{id}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable int id) {
        Optional<User> user = userService.getUserById((long) id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @Operation(summary = "Update a User by ID", description = "This endpoint updates the details of an existing user based on their unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid data provided for the update"),
            @ApiResponse(responseCode = "404", description = "User not found with the provided ID"),
            @ApiResponse(responseCode = "500", description = "Internal server error while processing the request")
    })


    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
        Optional<User> existingUser = userService.getUserById((long) id);
        if (existingUser.isPresent()) {
            User userToUpdate = existingUser.get();

            userToUpdate.setName(user.getName());
            userToUpdate.setEmail(user.getEmail());

            User updatedUser = userService.updateUser((long) id, userToUpdate);
            return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Delete a User by ID", description = "This endpoint deletes an existing user from the database using their unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found with the provided ID"),
            @ApiResponse(responseCode = "500", description = "Internal server error while processing the request")
    })

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        Optional<User> existingUser = userService.getUserById((long) id);
        if (existingUser.isPresent()) {
            userService.deleteUser((long) id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


}



