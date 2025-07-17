package com.chu.research_app.util;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    CHERCHEUR,
    RESPONSABLE,
    ADMINISTRATEUR;
    
    @Override
    public String getAuthority() {
        return "ROLE_" + name();
    }
}