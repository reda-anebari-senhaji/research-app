package com.chu.research_app.service;

import com.chu.research_app.dto.request.LoginRequest;
import com.chu.research_app.dto.request.SignupRequest;
import com.chu.research_app.entity.User;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> login(LoginRequest loginRequest);
    User signup(SignupRequest request);  // ✅ Retourne User, pas ResponseEntity
}