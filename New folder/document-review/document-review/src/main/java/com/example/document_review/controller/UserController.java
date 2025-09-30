package com.example.document_review.controller;
import com.example.document_review.dto.LoggedUserDto;
import com.example.document_review.dto.UserDto;
import com.example.document_review.entity.User;
import com.example.document_review.exception.EntityNotFoundException;
import com.example.document_review.exception.ValidationException;
import com.example.document_review.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import jakarta.servlet.http.Cookie;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
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
            return ResponseEntity.status(401).body(Map.of("error", "Not authenticated"));
        }

        User user = userService.findByUsername(principal.getName());
        if (user == null) {
            return ResponseEntity.status(404).body(Map.of("error", "User not found"));
        }

        return ResponseEntity.ok(Map.of(
                "username", user.getUsername(),
                "userId", user.getUserId()
        ));
    }

    @PostMapping("/login")
    public ResponseEntity<LoggedUserDto> login(@RequestBody UserDto userDto, HttpServletRequest request) throws EntityNotFoundException {

        try{
            User user = userService.loginUser(userDto);
            HttpSession session = request.getSession(true);

            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(user.getUsername(), null, new ArrayList<>());
            SecurityContextHolder.getContext().setAuthentication(authToken);
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
            return ResponseEntity.ok(new LoggedUserDto(user.getUsername()));
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextHolder.clearContext();
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return ResponseEntity.ok("Logout successful");
    }


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDto userDto) {
        User user = userService.findByUsername(userDto.getUsername());
        if (user != null) {
            return ResponseEntity.badRequest().body("Username already exists");
        }
        try{
            userService.register(userDto);
        }
        catch (ValidationException ve){
            return ResponseEntity.badRequest().body(ve.getMessage());
        }
        return ResponseEntity.ok("User registered successfully");
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Integer id) {
        UserDto user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseStatusException handleEntityNotFoundException(EntityNotFoundException ex) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
    }


}
