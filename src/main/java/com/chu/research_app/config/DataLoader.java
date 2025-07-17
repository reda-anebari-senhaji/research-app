package com.chu.research_app.config;

import com.chu.research_app.entity.User;
import com.chu.research_app.service.UserService;
import com.chu.research_app.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public void run(String... args) throws Exception {
        // Créer un utilisateur admin par défaut s'il n'existe pas
        if (!userService.existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(Role.ADMINISTRATEUR);
            userService.save(admin);
            System.out.println("Admin user created: username=admin, password=admin123");
        }
        
        // Créer un utilisateur chercheur par défaut s'il n'existe pas
        if (!userService.existsByUsername("chercheur")) {
            User chercheur = new User();
            chercheur.setUsername("chercheur");
            chercheur.setPassword(passwordEncoder.encode("chercheur123"));
            chercheur.setRole(Role.CHERCHEUR);
            userService.save(chercheur);
            System.out.println("Chercheur user created: username=chercheur, password=chercheur123");
        }
    }
}