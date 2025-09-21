package com.example.document_review.controller;

import com.example.document_review.dto.UserDto;
import com.example.document_review.service.UserService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/registration")
    public ResponseEntity<String> registraterUser(@RequestBody UserDto userDto) {
        userService.register(userDto);
        return ResponseEntity.ok("User registered successfully");

    }
    @GetMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam String username,
                                            @RequestParam String password) {
        UserDto userDto = new UserDto();
        userDto.setUsername(username);
        userDto.setPassword(password);
        userService.loginUser(userDto);
        return ResponseEntity.ok("User logged in successfully");
    }


}
