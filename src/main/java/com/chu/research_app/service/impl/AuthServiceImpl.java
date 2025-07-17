package com.chu.research_app.service.impl;

import com.chu.research_app.config.CustomUserDetails;
import com.chu.research_app.dto.request.LoginRequest;
import com.chu.research_app.dto.response.AuthResponse;
import com.chu.research_app.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Override
    public ResponseEntity<?> login(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(),
                    loginRequest.getPassword()
                )
            );
            
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            
            AuthResponse authResponse = new AuthResponse(
                "simple-token", // Token simple pour ce test
                userDetails.getUsername(),
                userDetails.getUser().getRole().name(),
                "Login successful"
            );
            
            return ResponseEntity.ok(authResponse);
            
        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body("Invalid credentials");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Login failed: " + e.getMessage());
        }
    }
}