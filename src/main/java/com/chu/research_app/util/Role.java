package com.chu.research_app.util;

import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,
    CHERCHEUR,
    ADMINISTRATEUR;

    @Override
    public String getAuthority() {
        return "ROLE_" + this.name();
    }

    @JsonValue
    public String getValue() {
        return this.name();
    }

    // ✅ Méthode pour créer à partir d'une string
    public static Role fromString(String role) {
        if (role == null || role.trim().isEmpty()) {
            throw new IllegalArgumentException("Role cannot be null or empty");
        }
        
        try {
            return Role.valueOf(role.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid role: " + role + ". Valid roles are: USER, CHERCHEUR, ADMINISTRATEUR");
        }
    }
}