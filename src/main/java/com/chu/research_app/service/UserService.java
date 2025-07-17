package com.chu.research_app.service;

import com.chu.research_app.entity.User;
import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    User save(User user);
}