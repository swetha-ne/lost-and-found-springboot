package com.example.lostandfound.controller;

import com.example.lostandfound.entity.User;
import com.example.lostandfound.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody User user) {
        logger.info("Processing signup for user: {}", user.getUsername());
        return ResponseEntity.ok(authService.signUp(user));
    }

    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@RequestBody User user) {  // Use DTO in production
        logger.info("Processing signin for user: {}", user.getUsername());
        return ResponseEntity.ok(authService.signIn(user.getUsername(), user.getPassword()));
    }
}