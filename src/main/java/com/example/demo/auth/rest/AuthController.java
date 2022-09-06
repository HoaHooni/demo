package com.example.demo.auth.rest;

import com.example.demo.auth.service.AuthService;
import com.example.demo.auth.model.request.LoginRequest;
import com.example.demo.auth.model.request.SignupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginRequest loginRequest) {

        return ResponseEntity.ok(authService.authenticateUser(loginRequest));
    }
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Validated @RequestBody SignupRequest signUpRequest) throws Exception {

        return ResponseEntity.ok(authService.registerUser(signUpRequest));
    }
}
