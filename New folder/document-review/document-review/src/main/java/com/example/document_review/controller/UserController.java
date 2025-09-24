package com.example.document_review.controller;
import com.example.document_review.dto.UserDto;
import com.example.document_review.entity.User;
import com.example.document_review.exception.EntityNotFoundException;
import com.example.document_review.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public ResponseEntity<?> home(Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(401).body("Not authenticated");
        }
        User user = userService.findByUsername(principal.getName());
        if (user == null) {
            return ResponseEntity.status(404).body("User not found");
        }

        return ResponseEntity.ok(user);
    }
    @PostMapping("/login")
    public ResponseEntity<String> loadUserByUsername(@RequestBody UserDto userDto) {
        try{
            User user = userService.loginUser(userDto);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
            }
            return ResponseEntity.ok("Login successful for user: " + user.getUsername());
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser(@RequestBody UserDto userDto, Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not logged in");
        }
        return ResponseEntity.ok("Logout successful for user: " + userDto.getUsername());
    }


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDto userDto) {
        User user = userService.findByUsername(userDto.getUsername());
        if (user != null) {
            return ResponseEntity.badRequest().body("Username already exists");
        }
        userService.register(userDto);
        return ResponseEntity.ok("User registered successfully");
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseStatusException handleEntityNotFoundException(EntityNotFoundException ex) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
    }


}
