package com.chu.research_app.service;

import com.chu.research_app.dto.request.LoginRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> login(LoginRequest loginRequest);
}