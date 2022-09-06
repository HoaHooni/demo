package com.example.demo.auth.service;

import com.example.demo.auth.model.request.LoginRequest;
import com.example.demo.auth.model.request.SignupRequest;
import com.example.demo.auth.model.respeonse.JwtResponse;
import com.example.demo.auth.model.respeonse.MessageResponse;

public interface AuthService {
    JwtResponse authenticateUser(LoginRequest request);
    MessageResponse registerUser(SignupRequest request) throws Exception;
}
