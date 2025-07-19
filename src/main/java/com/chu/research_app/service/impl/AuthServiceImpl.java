package com.chu.research_app.service.impl;

import com.chu.research_app.config.CustomUserDetails;
import com.chu.research_app.dto.request.LoginRequest;
import com.chu.research_app.dto.request.SignupRequest;
import com.chu.research_app.dto.response.AuthResponse;
import com.chu.research_app.entity.User;
import com.chu.research_app.repository.UserRepository;
import com.chu.research_app.service.AuthService;
import com.chu.research_app.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
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
    
    @Override
    public User signup(SignupRequest request) {
        // Vérifier si l'utilisateur existe déjà
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }
        
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }
        
        // Debug : afficher les données reçues
        System.out.println("🔍 DEBUG - SignupRequest reçu:");
        System.out.println("Username: " + request.getUsername());
        System.out.println("Role string: '" + request.getRole() + "'");
        System.out.println("Role length: " + (request.getRole() != null ? request.getRole().length() : "null"));
        
        // Convertir le rôle
        Role userRole;
        try {
            userRole = Role.fromString(request.getRole());  // ✅ request.getRole() est un String
            System.out.println("✅ Role converti: " + userRole);
        } catch (Exception e) {
            System.out.println("❌ Erreur conversion role: " + e.getMessage());
            throw new IllegalArgumentException("Invalid role: " + request.getRole());
        }
        
        // Créer l'utilisateur
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setRole(userRole);
        
        User savedUser = userRepository.save(user);
        System.out.println("✅ Utilisateur créé avec l'ID: " + savedUser.getId());
        
        return savedUser;
    }
}