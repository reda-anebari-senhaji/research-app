package com.chu.research_app.repository;

import com.chu.research_app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    boolean existsByUsername(String username);  // ✅ Ajouter cette méthode
    boolean existsByEmail(String email);        // ✅ Ajouter aussi celle-ci (optionnel mais utile)
}