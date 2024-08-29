package com.example.herbhub.service;

import com.example.herbhub.model.User;
import com.example.herbhub.repository.UserRepository;
import com.example.herbhub.util.Roles;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DataInitializationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        if (!userRepository.existsByUsername("admin")) {
            User user = new User();
            user.setPassword(passwordEncoder.encode("admin"));
            user.setUsername("admin");
            user.setEmail("admin");
            user.setRole(String.valueOf(Roles.ADMIN));
            userRepository.save(user);
        }
    }
}
