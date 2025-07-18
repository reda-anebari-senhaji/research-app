package com.chu.research_app.service;

import com.chu.research_app.dto.request.LoginRequest;
import com.chu.research_app.dto.request.SignupRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> login(LoginRequest loginRequest);
    ResponseEntity<?> signup(SignupRequest signupRequest);
}