package com.chu.research_app.controller;

import com.chu.research_app.dto.request.LoginRequest;
import com.chu.research_app.dto.request.SignupRequest;
import com.chu.research_app.dto.response.AuthResponse;
import com.chu.research_app.entity.User;
import com.chu.research_app.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4201"})
public class AuthController {
    
    @Autowired
    private AuthService authService;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }
    
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request) {
        try {
            System.out.println("🔍 DEBUG - Inscription reçue pour: " + request.getUsername());
            System.out.println("🔍 DEBUG - Role reçu: '" + request.getRole() + "'");
            
            User user = authService.signup(request);
            
            // Créer une réponse JSON structurée
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Account created successfully!");
            response.put("userId", user.getId());
            response.put("username", user.getUsername());
            response.put("role", user.getRole().name());
            
            return ResponseEntity.ok(response);
            
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Erreur validation: " + e.getMessage());
            
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", e.getMessage());
            
            return ResponseEntity.badRequest().body(errorResponse);
            
        } catch (Exception e) {
            System.out.println("❌ Erreur serveur: " + e.getMessage());
            e.printStackTrace();
            
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Error creating account: " + e.getMessage());
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
    
    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("Auth endpoint is working!");
    }
}